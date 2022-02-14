package com.ssafy.near.src.main.game.wordquiz

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentWordQuizBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WordQuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordQuizFragment : BaseFragment<FragmentWordQuizBinding>(R.layout.fragment_word_quiz) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var wordQuizViewModel: WordQuizViewModel
    private var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordQuizViewModel = ViewModelProvider(this).get(WordQuizViewModel::class.java)
        val quizNum = wordQuizViewModel.questionNum
        val images = wordQuizViewModel.images[quizNum]
        var imgIndex = 0

        timer.schedule(object: TimerTask() {
            override fun run() {
                if (imgIndex == images.size) {
                    imgIndex = 0
                }

                requireActivity().runOnUiThread {
                    Glide.with(requireActivity())
                        .load(images[imgIndex++])
                        .into(binding.ivQuestion)
                }
            }
        }, 1, 300)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WordQuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}