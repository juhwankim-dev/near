package com.ssafy.near.src.main.game.wordquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.repository.GameRepository

class WordQuizViewModelFactory(private val gameRepository: GameRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GameRepository::class.java).newInstance(gameRepository)
    }

}