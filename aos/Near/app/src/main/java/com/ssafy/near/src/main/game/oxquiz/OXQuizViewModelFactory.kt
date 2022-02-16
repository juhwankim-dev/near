package com.ssafy.near.src.main.game.oxquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OXQuizViewModelFactory () : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor().newInstance()
    }
}