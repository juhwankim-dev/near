package com.ssafy.near.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.util.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class HandSignRepository {
    private val TAG = "HandSignRepository"
    var _handSignList = MutableLiveData<List<HandSignInfo>>()
        private set
    var _bookmarkList = MutableLiveData<List<HandSignInfo>>()
        private set
    var _isAddBookmark = MutableLiveData<Boolean>()
        private set
    var _isDeleteBookmark = MutableLiveData<Boolean>()
        private set

    suspend fun loadHandSignList() {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.handSignService.loadHandSignList()
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _handSignList.postValue(response.body()!!.data)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }

        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun loadBookmarkList(id: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.handSignService.loadBookmarkList(id)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _bookmarkList.postValue(response.body()!!.data)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }

        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }
    suspend fun addBookmark(handcontent_key: String, id: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                var req = mutableMapOf<String, String>()
                req["handcontent_key"] = handcontent_key
                req["id"] = id
                RetrofitUtil.handSignService.addBookmark(req)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _isAddBookmark.postValue(response.body()!!.data!!)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }

        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }
    suspend fun deleteBookmark(handcontent_key: String, id: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                var req = mutableMapOf<String, String>()
                req["handcontent_key"] = handcontent_key
                req["id"] = id
                RetrofitUtil.handSignService.deleteBookmark(req)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _isDeleteBookmark.postValue(response.body()!!.data!!)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }

        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }
}