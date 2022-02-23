package com.ssafy.near.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.dto.RoomInfo
import com.ssafy.near.util.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class GameRepository {
    private val TAG = "GameRepository"
    var _roomInfo = MutableLiveData<RoomInfo?>()
        private set
    var _roomList = MutableLiveData<MutableList<RoomInfo>>()
        private set
    var _isDeleted = MutableLiveData<Boolean>()
        private set

    suspend fun createRoom(host: String, name: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                val req = mutableMapOf<String, String>()
                req["host"] = host
                req["name"] = name
                RetrofitUtil.gameService.createRoom(req)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _roomInfo.postValue(response.body())
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }

        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun loadRooms() {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.gameService.loadRooms()
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    val list = mutableListOf<RoomInfo>()
                    response.body()!!.data.forEach {
                        list.add(it)
                    }
                    _roomList.postValue(list)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun loadRoom(roomId: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.gameService.loadRoom(roomId)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _roomInfo.postValue(response.body()!!.data)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun deleteRoom(roomId: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.gameService.deleteRoom(roomId)
            }
            if (response.isSuccessful) {
                _isDeleted.postValue(response.body()!!.data!!)
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }
}