package com.ssafy.near.src.main.game.wordquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityWordQuizeBinding
import com.ssafy.near.dto.RoomInfo


class WordQuizActivity : BaseActivity<ActivityWordQuizeBinding>(R.layout.activity_word_quize) {
    private var roomInfo: RoomInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        roomInfo = intent.getSerializableExtra("roomInfo") as RoomInfo
        if (roomInfo == null) {
            finish()
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
}