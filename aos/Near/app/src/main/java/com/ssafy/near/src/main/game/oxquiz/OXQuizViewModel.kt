package com.ssafy.near.src.main.game.oxquiz

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel

class OXQuizViewModel : ViewModel() {
    val question = arrayOf("배고프다", "좋다", "감사합니다")
    var currentRound = 1
    var yourAnswer = ArrayList<Bitmap>()
    var yourScore = 10
}