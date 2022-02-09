package com.ssafy.near.src.main.game.wordquiz

import android.app.Activity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityWordQuizeBinding
import java.util.*

class WordQuizActivity : BaseActivity<ActivityWordQuizeBinding>(R.layout.activity_word_quize) {
    private var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val images = arrayOf(R.drawable.ic_bookmark, R.drawable.ic_award, R.drawable.ic_bookmark_add, R.drawable.ic_edu)
        var index = 0

        timer.schedule(object: TimerTask() {
            override fun run() {
                if (index == images.size) {
                    index = 0
                }

                runOnUiThread {
                    Glide.with(this@WordQuizActivity)
                        .load(images[index++])
                        .into(binding.ivQuestion)
                }
            }
        }, 0, 300)
    }
}