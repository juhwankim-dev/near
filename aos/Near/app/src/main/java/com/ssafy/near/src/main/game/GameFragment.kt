package com.ssafy.near.src.main.game

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentGameBinding
import com.ssafy.near.src.login.LoginActivity
import com.ssafy.near.src.main.game.oxquiz.OXQuizActivity
import com.ssafy.near.src.main.game.wordquiz.RoomActivity
import com.ssafy.near.util.SharedPreferencesUtil.Companion.DEFAULT_ID

class GameFragment : BaseFragment<FragmentGameBinding>(R.layout.fragment_game) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTemp.setOnClickListener {
            enterGame(OXQuizActivity::class.java)
        }
        binding.btnWord.setOnClickListener {
            enterGame(RoomActivity::class.java)
        }
    }

    private fun enterGame(cls: Class<*>) {
        //if(sSharedPreferences.getUserId() == DEFAULT_ID) {
        //    showToastMessage("로그인을 해주세요")
        //    startActivity(Intent(requireContext(), LoginActivity::class.java))
        //} else {
            startActivity(Intent(requireContext(), cls))
        //}
    }
}