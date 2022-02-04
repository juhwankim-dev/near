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
}