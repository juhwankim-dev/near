package com.ssafy.near.src.main.game.wordquiz.playing

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentWordQuizBinding
import com.ssafy.near.dto.GameUser
import com.ssafy.near.dto.Message
import com.ssafy.near.dto.MsgType
import com.ssafy.near.dto.RoomInfo
import com.ssafy.near.repository.GameRepository
import com.ssafy.near.src.main.fingersign.FingerSignAdapter
import com.ssafy.near.src.main.game.CustomWordTextView
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModel
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModelFactory
import kotlinx.coroutines.*
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
    private lateinit var userList: ArrayList<GameUser>
    private lateinit var nickname: String

    private lateinit var pbTimer: Timer
    private var timer = Timer()

    private val ivCrownList = ArrayList<ImageView>()
    private val tvUserNameList = ArrayList<TextView>()
    private val ivUserList = ArrayList<ImageView>()
    private val tvUserScoreList = ArrayList<TextView>()

    lateinit var chatAdapter: ChatAdapter

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
            userList = it.getStringArrayList("userList") as ArrayList<GameUser>
            nickname = it.getString("nickname") as String
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()
        showReady()
    }

    override fun onDetach() {
        super.onDetach()
        timer.cancel()
        pbTimer.cancel()
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
                tvUserNameList[i].text = userList[i].name
                tvUserScoreList[i].text = "0 점"
                when (userList[i].avatar) {
                    0 -> ivUserList[i].setImageResource(R.drawable.img_avatar_1)
                    1 -> ivUserList[i].setImageResource(R.drawable.img_avatar_2)
                    2 -> ivUserList[i].setImageResource(R.drawable.img_avatar_3)
                }
            } else {
                ivCrownList[i].visibility = View.GONE
                tvUserNameList[i].visibility = View.GONE
                tvUserScoreList[i].visibility = View.GONE
                ivUserList[i].visibility = View.GONE
            }
        }

        chatAdapter = ChatAdapter()
        binding.rvChat.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatAdapter
        }
        chatAdapter.addStartMessage()
    }

    private fun initEvent() {
        binding.ivExitGame.setOnClickListener {
            // 방장이 나가면 방이 폭파되는 등의 문제로 나가기 구현 안 해놓음.
        }

        binding.etYourAnswer.setOnKeyListener { view, keyCode, keyEvent ->
            if ((keyEvent.action == KeyEvent.ACTION_UP) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                val input = binding.etYourAnswer.text.toString().replace(" ", "")

                // 입력한 텍스트가 정답이라면
                if (input == wordQuizViewModel.question[wordQuizViewModel.getQNum().value!!]) {
                    wordQuizViewModel.sendMessage(MsgType.ANSWER, roomInfo.roomId, nickname, input)

                    CoroutineScope(Dispatchers.Main).launch {
                        val job = CoroutineScope(Dispatchers.IO).async {
                            delay(100)
                        }

                        job.join()
                        wordQuizViewModel.sendMessage(MsgType.NOTICE, roomInfo.roomId, nickname, "100")
                        binding.etYourAnswer.isEnabled = false
                    }
                }

                // 입력한 텍스트가 정답이 아니라면
                else {
                    if(input.isNotEmpty()) {
                        wordQuizViewModel.sendMessage(MsgType.TALK, roomInfo.roomId, nickname, input)
                    }
                }

                binding.etYourAnswer.setText("")
            }
            true
        }

        binding.lottieViewReadygo.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                binding.viewOpaqueScreen.visibility = View.GONE
                binding.lottieViewReadygo.visibility = View.GONE
                binding.ivQuestion.visibility = View.VISIBLE
                initViewModel()
            }

            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}
        })
    }

    private fun showReady() {
        binding.ivQuestion.visibility = View.INVISIBLE
        binding.viewOpaqueScreen.visibility = View.VISIBLE
        binding.lottieViewHourglass.visibility = View.INVISIBLE
        binding.lottieViewReadygo.apply {
            visibility = View.VISIBLE
            playAnimation()
        }
    }

    private fun initViewModel() {
        wordQuizViewModel.initUser(userList)

        wordQuizViewModel.getQNum().observe(viewLifecycleOwner) {
            startQuiz(wordQuizViewModel.images[it], wordQuizViewModel.question[it])
        }

        wordQuizViewModel.getMessage().observe(viewLifecycleOwner) {
            when(it.type) {
                // 메시지 타입이 ANSWER 라면 (정답을 맞춘거라면)
                MsgType.ANSWER -> {
                    // 정답을 맞춘게 나라면
                    if(it.sender == nickname) {
                        chatAdapter.updateNewMsg(it)
                    }
                }

                MsgType.NOTICE -> {
                    wordQuizViewModel.updateUserScore(it.sender, it.message.toInt())
                    chatAdapter.updateNewMsg(it)
                }

                // 메시지 타입이 TALK 라면 (정답이 아니라 일반 메시지)
                MsgType.TALK -> {
                    chatAdapter.updateNewMsg(it)
                }
            }
            binding.rvChat.scrollToPosition(chatAdapter.itemCount - 1)
        }
    }

    private fun startQuiz(images: Array<Int>, quiz: String) {
        var imgIndex = 0
        var timerStart = true
        binding.lottieViewHourglass.visibility = View.INVISIBLE
        binding.pbTimer.progress = 1000
        binding.etYourAnswer.setText("")
        binding.etYourAnswer.isEnabled = true
        setWord(quiz)

        timer = timer(period = 500) {
            if (imgIndex == images.size) {
                imgIndex = 0

                if (timerStart) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        showToastMessage("start!!")
                        binding.lottieViewHourglass.apply {
                            visibility = View.VISIBLE
                            playAnimation()
                        }
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
            CoroutineScope(Dispatchers.Main).launch {
                (context as WordQuizActivity).onChangeFragment(WordResultFragment.newInstance(roomInfo, userList))
            }
        }
    }

    private fun updateScoreView() {
        var max = 0
        var maxIdx = -1
        for (i in userList.indices) {
            val score = wordQuizViewModel.getUserScore(userList[i].name)!!
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

    private fun setWord(word: String) {
        binding.layoutWord.removeAllViews()

        for (i in word.indices) {
            binding.layoutWord.addView(createWord())
        }
    }

    private fun createWord(): View {
        val customWordTextView = CustomWordTextView(requireContext())
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        customWordTextView.layoutParams = lp
        customWordTextView.id = ViewCompat.generateViewId()
        return customWordTextView
    }

    companion object {
        @JvmStatic
        fun newInstance(roomInfo: RoomInfo, userList: ArrayList<GameUser>, nickname: String) =
            WordQuizFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("roomInfo", roomInfo)
                    putSerializable("userList", userList)
                    putSerializable("nickname", nickname)
                }
            }
    }
}