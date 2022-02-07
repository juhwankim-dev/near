package com.ssafy.near.src.main.game.oxquiz

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentResultBinding

class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    private lateinit var oxQuizViewModel: OXQuizViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        oxQuizViewModel = ViewModelProvider(requireActivity()).get(OXQuizViewModel::class.java)

        initView()
        initEvent()
    }

    fun initView() {
        var score = oxQuizViewModel.yourScore
        binding.score = "${score}점"

        if(score >= 50) {
            binding.ivSecondStar.setImageResource(R.drawable.img_star_active)

            if(score == 100) {
                binding.ivThirdStar.setImageResource(R.drawable.img_star_active)
            }
        }
    }

    fun initEvent() {
        binding.btnExit.setOnClickListener {
            (context as OXQuizActivity).finish()
        }
    }
}