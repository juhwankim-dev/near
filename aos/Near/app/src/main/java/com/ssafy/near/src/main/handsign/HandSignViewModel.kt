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
    private val bookmarkList = handSignRepository._bookmarkList
    private val isAddBookmark = handSignRepository._isAddBookmark
    private val isDeleteBookmark = handSignRepository._isDeleteBookmark

    fun getHandSignList(): MutableLiveData<List<HandSignInfo>> {
        return handSignList
    }

    fun getbookmarkList(): MutableLiveData<List<HandSignInfo>> {
        return bookmarkList
    }

    fun getAddBookmark(): MutableLiveData<Boolean> {
        return isAddBookmark
    }

    fun getDeleteBookmark(): MutableLiveData<Boolean> {
        return isDeleteBookmark
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

    fun addBookmark(handcontent_key: String, id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            handSignRepository.addBookmark(handcontent_key, id)
        }
    }

    fun deleteBookmark(handcontent_key: String, id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            handSignRepository.deleteBookmark(handcontent_key, id)
        }
    }
}