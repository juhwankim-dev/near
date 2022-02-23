package com.ssafy.near.src.main.game.wordquiz.playing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityWordQuizeBinding
import com.ssafy.near.dto.MsgType
import com.ssafy.near.dto.RoomInfo
import com.ssafy.near.repository.GameRepository
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModel
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModelFactory


class WordQuizActivity : BaseActivity<ActivityWordQuizeBinding>(R.layout.activity_word_quize) {
    private var roomInfo: RoomInfo? = null
    private val wordQuizViewModel: WordQuizViewModel by lazy {
        ViewModelProvider(this, WordQuizViewModelFactory(
            GameRepository()))
            .get(WordQuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        roomInfo = intent.getSerializableExtra("roomInfo") as RoomInfo
        if (roomInfo == null) {
            showToastMessage("존재하지 않는 방입니다.")
            finish()
        }

        wordQuizViewModel.apply {
            connect(roomInfo!!.roomId)
            getIsDeleted().observe(this@WordQuizActivity) {
                if (it) finish()
            }
        }

        //초기 실행화면 설정
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_word_quiz)

        if (currentFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_word_quiz, WaitingRoomFragment.newInstance(roomInfo!!))
                .commit()
        }
    }

    fun onChangeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_word_quiz, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        wordQuizViewModel.disconnect()
    }

    override fun onBackPressed() {
        showToastMessage("뒤로가기 버튼을 사용할 수 없습니다.")
    }

    fun exitRoom() {
        val nickname = ApplicationClass.sSharedPreferences.getNickname()
        wordQuizViewModel.apply {
            sendMessage(MsgType.OUT, roomInfo!!.roomId, nickname, "")
            if (nickname == roomInfo!!.host)
                deleteRoom(roomInfo!!.roomId)
            else {
                finish()
            }
        }
    }
}