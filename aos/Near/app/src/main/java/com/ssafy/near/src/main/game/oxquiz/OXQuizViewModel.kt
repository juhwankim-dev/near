package com.ssafy.near.src.main.game.oxquiz

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel

class OXQuizViewModel : ViewModel() {
    val question = arrayOf("반갑습니다", "감사합니다", "아니요", "그립다", "좋다")
    var currentRound = 1
    var yourAnswer = ArrayList<Bitmap>()
    var yourScore = 0
}