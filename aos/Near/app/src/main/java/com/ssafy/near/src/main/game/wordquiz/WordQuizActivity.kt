package com.ssafy.near.src.main.game.wordquiz

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityWordQuizeBinding
import java.util.*

class WordQuizActivity : BaseActivity<ActivityWordQuizeBinding>(R.layout.activity_word_quize) {
    private lateinit var wordQuizeViewModel: WordQuizViewModel
    private var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        wordQuizeViewModel = ViewModelProvider(this).get(WordQuizViewModel::class.java)
        val quizNum = wordQuizeViewModel.questionNum
        val images = wordQuizeViewModel.images[quizNum]
        var imgIndex = 0

        timer.schedule(object: TimerTask() {
            override fun run() {
                if (imgIndex == images.size) {
                    imgIndex = 0
                }

                runOnUiThread {
                    Glide.with(this@WordQuizActivity)
                        .load(images[imgIndex++])
                        .into(binding.ivQuestion)
                }
            }
        }, 1, 300)
    }
}