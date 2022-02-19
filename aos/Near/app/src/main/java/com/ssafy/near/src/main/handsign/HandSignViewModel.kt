package com.ssafy.near.src.main.handsign

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.repository.HandSignRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HandSignViewModel(private val handSignRepository : HandSignRepository) : ViewModel() {
    var selectedHandSignInfo: HandSignInfo? = null

    private val handSignList = handSignRepository._handSignList
    private val bookmarkList = handSignRepository._bookmarkList
    private val isBookmark = handSignRepository._isBookmark


    fun getHandSignList(): MutableLiveData<List<HandSignInfo>> {
        return handSignList
    }

    fun getBookmarkList(): MutableLiveData<List<HandSignInfo>> {
        return bookmarkList
    }

    fun getBookmark(): MutableLiveData<Boolean> {
        return isBookmark
    }

    fun loadHandSignList() {
        viewModelScope.launch(Dispatchers.IO) {
            handSignRepository.loadHandSignList()
        }
    }

    fun loadBookmarkList(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            handSignRepository.loadBookmarkList(id)
        }
    }

    fun addBookmark(handContentKey: String, id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            handSignRepository.addBookmark(handContentKey, id)
        }
    }

    fun deleteBookmark(handContentKey: String, id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            handSignRepository.deleteBookmark(handContentKey, id)
        }
    }
}