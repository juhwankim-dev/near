package com.ssafy.near.src

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.repository.SampleRepository

class SampleViewModelFactory (private val sampleRepository : SampleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SampleRepository::class.java).newInstance(sampleRepository)
    }
}