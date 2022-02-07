package com.ssafy.near.src.main.game

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentGameBinding
import com.ssafy.near.src.main.game.oxquiz.OXQuizActivity

class GameFragment : BaseFragment<FragmentGameBinding>(R.layout.fragment_game) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTemp.setOnClickListener {
            startActivity(Intent(requireContext(), OXQuizActivity::class.java))
        }
    }
}