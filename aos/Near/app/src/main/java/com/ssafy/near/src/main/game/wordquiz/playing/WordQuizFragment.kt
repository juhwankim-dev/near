package com.ssafy.near.src.main.game.wordquiz.playing

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentWordQuizBinding
import com.ssafy.near.dto.MsgType
import com.ssafy.near.dto.RoomInfo
import com.ssafy.near.repository.GameRepository
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModel
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer


class WordQuizFragment : BaseFragment<FragmentWordQuizBinding>(R.layout.fragment_word_quiz) {
    private val TAG = "WordQuizFragment"

    private val wordQuizViewModel: WordQuizViewModel by lazy {
        ViewModelProvider(requireActivity(), WordQuizViewModelFactory(GameRepository()))
            .get(WordQuizViewModel::class.java)
    }

    private lateinit var callback: OnBackPressedCallback

    private lateinit var roomInfo: RoomInfo
    private lateinit var userList: ArrayList<String>
    private lateinit var nickname: String

    private lateinit var pbTimer: Timer
    private var timer = Timer()

    private val ivCrownList = ArrayList<ImageView>()
    private val tvUserNameList = ArrayList<TextView>()
    private val ivUserList = ArrayList<ImageView>()
    private val tvUserScoreList = ArrayList<TextView>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.i(TAG, "handleOnBackPressed: back button 금지")
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            roomInfo = it.getSerializable("roomInfo") as RoomInfo
            userList = it.getStringArrayList("userList") as ArrayList<String>
            nickname = it.getString("nickname") as String
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
        initEvent()
    }

    override fun onDetach() {
        super.onDetach()
        timer.cancel()
        pbTimer.cancel()
    }

    private fun initViewModel() {
        wordQuizViewModel.initUser(userList)

        wordQuizViewModel.getQNum().observe(viewLifecycleOwner) {
            startQuiz(wordQuizViewModel.images[it])
        }

        wordQuizViewModel.getMessage().observe(viewLifecycleOwner) {
            if (it.type == MsgType.TALK) {
                wordQuizViewModel.updateUserScore(it.sender, it.message.toInt())
            }
        }
    }

    private fun initView() {
        ivCrownList.add(binding.ivCrown1)
        ivCrownList.add(binding.ivCrown2)
        ivCrownList.add(binding.ivCrown3)
        ivCrownList.add(binding.ivCrown4)

        ivUserList.add(binding.ivUser1)
        ivUserList.add(binding.ivUser2)
        ivUserList.add(binding.ivUser3)
        ivUserList.add(binding.ivUser4)

        tvUserNameList.add(binding.tvUser1Name)
        tvUserNameList.add(binding.tvUser2Name)
        tvUserNameList.add(binding.tvUser3Name)
        tvUserNameList.add(binding.tvUser4Name)

        tvUserScoreList.add(binding.tvUser1Score)
        tvUserScoreList.add(binding.tvUser2Score)
        tvUserScoreList.add(binding.tvUser3Score)
        tvUserScoreList.add(binding.tvUser4Score)

        for (i in tvUserNameList.indices) {
            if (i < userList.size) {
                ivCrownList[i].visibility = View.INVISIBLE
                tvUserNameList[i].text = userList[i]
                tvUserScoreList[i].text = "0 점"
            } else {
                ivCrownList[i].visibility = View.GONE
                tvUserNameList[i].visibility = View.GONE
                ivUserList[i].visibility = View.GONE
                tvUserScoreList[i].visibility = View.GONE
            }
        }

        when(wordQuizViewModel.selectedAvatar) {
            0 -> binding.ivUser1.setImageResource(R.drawable.img_avatar_1)
            1 -> binding.ivUser1.setImageResource(R.drawable.img_avatar_2)
            2 -> binding.ivUser1.setImageResource(R.drawable.img_avatar_3)
        }
    }

    private fun initEvent() {
        binding.ivExitGame.setOnClickListener {
            // 방장이 나가면 방이 폭파되는 등의 문제로 나가기 구현 안 해놓음.
        }

        binding.etYourAnswer.setOnKeyListener { view, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                val answer = binding.etYourAnswer.text.toString().replace(" ", "")
                if (answer == wordQuizViewModel.question[wordQuizViewModel.getQNum().value!!]) {
                    wordQuizViewModel.sendMessage(MsgType.TALK, roomInfo.roomId, nickname, "100")
                }
                binding.etYourAnswer.setText("")
                binding.etYourAnswer.isEnabled = false
            }
            true
        }
    }

    private fun startQuiz(images: Array<Int>) {
        var imgIndex = 0
        var timerStart = true
        binding.pbTimer.progress = 1000
        binding.etYourAnswer.setText("")
        binding.etYourAnswer.isEnabled = true

        timer = timer(period = 500) {
            if (imgIndex == images.size) {
                imgIndex = 0

                if (timerStart) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        showToastMessage("start!!")
                    }, 0)
                    pbTimer = timer(period = 30) {
                        binding.pbTimer.incrementProgressBy(-1)
                        if (binding.pbTimer.progress == 0) {
                            moveNextQuiz()
                        }
                    }
                    timerStart = false
                }
            }

            requireActivity().runOnUiThread {
                Glide.with(requireActivity())
                    .load(images[imgIndex++])
                    .into(binding.ivQuestion)
            }
        }
    }

    private fun moveNextQuiz() {
        timer.cancel()
        pbTimer.cancel()
        Handler(Looper.getMainLooper()).postDelayed({
            updateScoreView()
        }, 0)

        if (wordQuizViewModel.getQNum().value!! < wordQuizViewModel.question.size - 1) {
            wordQuizViewModel.nextQuiz()
        } else {
            // 결과화면
//            Handler(Looper.getMainLooper()).postDelayed({
//                showToastMessage("${wordQuizViewModel.getUserScore(nickname)}")
//            }, 0)
            CoroutineScope(Dispatchers.Main).launch {
               // wordQuizViewModel.sendMessage(MsgType.END, roomInfo.roomId, "", "")
                //delay(3000)

                (context as WordQuizActivity).onChangeFragment(WordResultFragment())
            }
        }
    }

    private fun updateScoreView() {
        var max = 0
        var maxIdx = -1
        for (i in userList.indices) {
            val score = wordQuizViewModel.getUserScore(userList[i])!!
            tvUserScoreList[i].text = "$score 점"

            if (score > max) {
                max = score
                maxIdx = i
            }
        }

        for (i in userList.indices) {
            if (i == maxIdx) {
                ivCrownList[i].visibility = View.VISIBLE
            } else if (maxIdx >= 0) {
                ivCrownList[i].visibility = View.INVISIBLE
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(roomInfo: RoomInfo, userList: ArrayList<String>, nickname: String) =
            WordQuizFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("roomInfo", roomInfo)
                    putSerializable("userList", userList)
                    putSerializable("nickname", nickname)
                }
            }
    }
}