package com.ssafy.near.src.main.handsign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.repository.HandSignRepository

class HandSignViewModelFactory (private val handSignRepository : HandSignRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HandSignRepository::class.java).newInstance(handSignRepository)
    }
}