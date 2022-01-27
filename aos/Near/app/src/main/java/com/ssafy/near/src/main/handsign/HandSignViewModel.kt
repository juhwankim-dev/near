package com.ssafy.near.src.main.handsign

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.repository.HandSignRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HandSignViewModel(private val handSignRepository : HandSignRepository) : ViewModel() {
    private val handSignList = handSignRepository._handSignList

    fun getHandSignList(): MutableLiveData<List<HandSignInfo>> {
        return handSignList
    }

    fun loadHandSignList() {
        viewModelScope.launch(Dispatchers.IO) {
            handSignRepository.loadHandSignList()
        }
    }
}