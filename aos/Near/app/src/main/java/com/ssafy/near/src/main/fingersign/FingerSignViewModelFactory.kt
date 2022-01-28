package com.ssafy.near.src.main.fingersign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.repository.FingerSignRepository

class FingerSignViewModelFactory (private val fingerSignRepository: FingerSignRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FingerSignRepository::class.java).newInstance(fingerSignRepository)
    }
}