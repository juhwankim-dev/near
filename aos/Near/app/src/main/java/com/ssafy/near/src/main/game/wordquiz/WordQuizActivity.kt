package com.ssafy.near.src.main.game.wordquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityWordQuizeBinding


class WordQuizActivity : BaseActivity<ActivityWordQuizeBinding>(R.layout.activity_word_quize) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //초기 실행화면 설정
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_word_quiz)

        if (currentFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_word_quiz, WaitingRoomFragment())
                .commit()
        }
    }

    fun onChangeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_word_quiz, fragment)
            .addToBackStack(null)
            .commit()
    }
}