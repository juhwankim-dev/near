package com.ssafy.near.src

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.near.dto.SampleResponse
import com.ssafy.near.repository.SampleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SampleViewModel(private val sampleRepository : SampleRepository) : ViewModel() {
    private val sampleResponse = sampleRepository._sampleResponse

    fun selectSamples(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            sampleRepository.selectSamples(page)
        }
    }

    fun getSampleResponse(): LiveData<SampleResponse> {
        return sampleResponse
    }
}