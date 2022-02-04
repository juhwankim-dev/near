package com.ssafy.near.src.main.fingersign

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.near.dto.FingerSignInfo
import com.ssafy.near.repository.FingerSignRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FingerSignViewModel(private val fingerSIgnRepository: FingerSignRepository) : ViewModel() {
    private val fingerSignList = fingerSIgnRepository._fingerSignList

    fun getFingerSignList(): MutableLiveData<List<FingerSignInfo>> {
        return fingerSignList
    }

    fun loadFingerSignList() {
        viewModelScope.launch(Dispatchers.IO) {
            fingerSIgnRepository.loadFingerSignList()
        }
    }
}