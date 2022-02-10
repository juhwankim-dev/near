package com.ssafy.near.src.main.game.wordquiz

import androidx.lifecycle.ViewModel
import com.ssafy.near.R

class WordQuizViewModel : ViewModel() {
    val question = arrayOf("서울시", "삼성")
    val images = arrayOf(
        arrayOf(R.drawable.ma_010, R.drawable.ma_024, R.drawable.ma_012, R.drawable.ma_033, R.drawable.ma_006, R.drawable.ma_010, R.drawable.ma_040),
        arrayOf(R.drawable.ma_010, R.drawable.ma_020, R.drawable.ma_007, R.drawable.ma_010, R.drawable.ma_024, R.drawable.ma_012)
    )
    var questionNum = 0

}