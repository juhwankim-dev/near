package com.ssafy.near.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.dto.SignResponse
import com.ssafy.near.dto.UserInfoResponse
import com.ssafy.near.util.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserRepository {
    private val TAG = "UserRepository"
    var _signResponse = MutableLiveData<SignResponse>()
        private set
    var _checkedId = MutableLiveData<Boolean>()
        private set
    var _checkedNickname = MutableLiveData<Boolean>()
        private set
    var _checkedEmail = MutableLiveData<Boolean>()
        private set
    var _userInfo = MutableLiveData<UserInfoResponse>()
        private set


    suspend fun login(uid: String, pw: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.userService.login("none", uid, pw)
            }

            if (response.isSuccessful) {
                if (response.body() != null) {
                    val userResponse = response.body()!!
                    if (userResponse.output > 0) {
                        val token = userResponse.userToken
                        ApplicationClass.sSharedPreferences.addUser(token)
                    }
                    _signResponse.postValue(userResponse)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun signUp(uid: String, nickname: String, email: String, pw: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.userService.signUp("none", uid, nickname, email, pw)
            }
            if (response.isSuccessful) {
                Log.d(TAG, "signUp: $response")
                if (response.body() != null) {
                    _signResponse.postValue(response.body())
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun checkDuplicatedId(uid: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.userService.checkId(uid)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _checkedId.postValue(response.body()!!.isDuplicated)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun checkDuplicatedNickname(nickname: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.userService.checkNickname(nickname)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Log.d(TAG, "checkDuplicatedNickname: $response")
                    _checkedNickname.postValue(response.body()!!.isDuplicated)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun checkDuplicatedEmail(email: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.userService.checkEmail(email)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Log.d(TAG, "checkDuplicatedNickname: $response")
                    _checkedEmail.postValue(response.body()!!.isDuplicated)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun getUserInfo(token: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.userService.getUserInfo(token)
            }

            if (response.isSuccessful) {
                if (response.body() != null) {
                    _userInfo.postValue(response.body())
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }
}