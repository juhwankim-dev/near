package com.ssafy.near.src.main.game.oxquiz

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentCorrectBinding
import java.util.*

class CorrectFragment : BaseFragment<FragmentCorrectBinding>(R.layout.fragment_correct) {
    private lateinit var oxQuizViewModel: OXQuizViewModel
    private var timer = Timer()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        oxQuizViewModel = ViewModelProvider(requireActivity(), OXQuizViewModelFactory())
            .get(OXQuizViewModel::class.java)

        initView()
        initEvent()
    }

    fun initView() {
        timer.schedule(object: TimerTask() {
            var index = 0
            override fun run() {
                if (index == oxQuizViewModel.yourAnswer.size) {
                    index = 0
                }

                requireActivity().runOnUiThread {
                    binding.ivYourAnswer.setImageBitmap(oxQuizViewModel.yourAnswer[index++])
                }
            }
        }, 0, 300)
    }

    fun initEvent() {
        binding.lottieViewCongratulation.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                binding.lottieViewCongratulation.visibility = View.GONE
            }

            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}
        })

        binding.btnNextRound.setOnClickListener {
            timer.cancel()
            oxQuizViewModel.currentRound++
            if(oxQuizViewModel.currentRound <= oxQuizViewModel.question.size) {
                (context as OXQuizActivity).onChangeFragment(OXQuizFragment())
            } else {
                (context as OXQuizActivity).onChangeFragment(ResultFragment())
            }
        }
    }
}