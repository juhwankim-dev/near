package com.ssafy.near.src.main.game.wordquiz.playing

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.View
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
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer


class WordQuizFragment : BaseFragment<FragmentWordQuizBinding>(R.layout.fragment_word_quiz) {
//    private lateinit var roomInfo: RoomInfo
//    private lateinit var userList: ArrayList<String>
//    private lateinit var nickname: String

    private lateinit var wqViewModel: WordQuizViewModel
    private lateinit var pbTimer: Timer
    private var timer = Timer()

//    private val ivCrownList = arrayOf(binding.ivCrown1, binding.ivCrown2, binding.ivCrown3, binding.ivCrown4)
//    private val tvUserNameList = arrayOf(binding.tvUser1Name, binding.tvUser2Name, binding.tvUser3Name, binding.tvUser4Name)
//    private val ivUserList = arrayOf(binding.ivUser1, binding.ivUser2, binding.ivUser3, binding.ivUser4)
//    private val tvUserScoreList = arrayOf(binding.tvUser1Score, binding.tvUser2Score, binding.tvUser3Score, binding.tvUser4Score)


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            roomInfo = it.getSerializable("roomInfo") as RoomInfo
//            userList = it.getStringArrayList("userList") as ArrayList<String>
//            nickname = it.getString("nickname") as String
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
//        initView()
        initEvent()
    }

    private fun initViewModel() {
        wqViewModel = ViewModelProvider(this, WordQuizViewModelFactory(GameRepository()))
            .get(WordQuizViewModel::class.java)

        wqViewModel.apply {
//            connectSocket()
//            connect(roomInfo.roomId)
//            initUSer(userList)
        }

        wqViewModel.getQNum().observe(viewLifecycleOwner) {
            startQuiz(wqViewModel.images[it])
        }

        wqViewModel.getMessage().observe(viewLifecycleOwner) {
            if (it.type == MsgType.COMM) {
                wqViewModel.updateUserScore(it.sender, it.message.toInt())
            }
        }
    }

//    private fun initView() {
//        for (i in tvUserNameList.indices) {
//            if (i < userList.size) {
//                ivCrownList[i].visibility = View.INVISIBLE
//                tvUserNameList[i].text = userList[i]
//                tvUserScoreList[i].text = "0 점"
//            } else {
//                ivCrownList[i].visibility = View.GONE
//                tvUserNameList[i].visibility = View.GONE
//                ivUserList[i].visibility = View.GONE
//                tvUserScoreList[i].visibility = View.GONE
//            }
//        }
//    }

    private fun initEvent() {
        binding.etYourAnswer.setOnKeyListener { view, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                val answer = binding.etYourAnswer.text.toString().replace(" ", "")
//                if (answer == wqViewModel.question[wqViewModel.getQNum().value!!]) {
//                    wqViewModel.sendMessage(MsgType.COMM, roomInfo.roomId, nickname, "100")
//                }
                binding.etYourAnswer.setText("")
                binding.etYourAnswer.isEnabled = false
            }
            true
        }
    }

    private fun startQuiz(images: Array<Int>) {
        var imgIndex = 0
        var timerStart = true
        binding.pbTimer.progress = 10000
        binding.etYourAnswer.setText("")
        binding.etYourAnswer.isEnabled = true

        timer = timer(period = 500) {
            if (imgIndex == images.size) {
                imgIndex = 0

                if (timerStart) {
                    // 로티로 바꿈?
                    Handler(Looper.getMainLooper()).postDelayed({
                        showToastMessage("start!!")
                    }, 0)
                    pbTimer = timer(period = 100) {
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
        updateScoreView()

        if (wqViewModel.getQNum().value!! < wqViewModel.question.size - 1) {
            wqViewModel.nextQuiz()
        } else {
            // 결과화면
//            Handler(Looper.getMainLooper()).postDelayed({
//                showToastMessage("${wqViewModel.getUserScore(nickname)}")
//            }, 0)
        }
    }

    private fun updateScoreView() {
//        var max = 0
//        var maxIdx = 0
//        for (i in userList.indices) {
//            val score = wqViewModel.getUserScore(userList[i])!!
//            tvUserScoreList[i].text = "$score 점"
//
//            if (score > max) {
//                max = score
//                maxIdx = i
//            }
//        }
//
//        for (i in userList.indices) {
//            if (i == maxIdx) {
//                ivCrownList[i].visibility = View.VISIBLE
//            } else {
//                ivUserList[i].visibility = View.INVISIBLE
//            }
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        pbTimer.cancel()
        wqViewModel.disconnect()
    }

//    companion object {
//        @JvmStatic
//        fun newInstance(roomInfo: RoomInfo, userList: ArrayList<String>, nickname: String) =
//            WaitingRoomFragment().apply {
//                arguments = Bundle().apply {
//                    putSerializable("roomInfo", roomInfo)
//                    putSerializable("userList", userList)
//                    putSerializable("nickname", nickname)
//                }
//            }
//    }
}