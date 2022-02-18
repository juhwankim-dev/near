package com.ssafy.near.src.main.game.oxquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityOxquizBinding

class OXQuizActivity : BaseActivity<ActivityOxquizBinding>(R.layout.activity_oxquiz) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //초기 실행화면 설정
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_ox_quiz)

        if(currentFragment == null){
            val fragment = OXQuizFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_ox_quiz, fragment)
                .commit()
        }
    }

    fun onChangeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_ox_quiz, fragment)
            .addToBackStack(null)
            .commit()
    }
}