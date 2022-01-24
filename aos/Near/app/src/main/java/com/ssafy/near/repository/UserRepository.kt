package com.ssafy.near.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.dto.SignResponse
import com.ssafy.near.dto.UserInfo
import com.ssafy.near.util.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserRepository {
    private val TAG = "UserRepository"
    var _signResponse = MutableLiveData<SignResponse>()
        private set
    var _isCheckedId = MutableLiveData<Boolean>()
        private set
    var _isCheckedNickname = MutableLiveData<Boolean>()
        private set
    var _isCheckedEmail = MutableLiveData<Boolean>()
        private set
    var _isCheckedPw = MutableLiveData<Boolean>()
        private set
    var _userInfo = MutableLiveData<UserInfo?>()
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
                        sSharedPreferences.addUser(token)
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

    suspend fun loadUserInfo(token: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.userService.loadUserInfo(token)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _userInfo.postValue(response.body()?.userInfo)
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
                    _isCheckedId.postValue(response.body()!!.isDuplicated)
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
                    _isCheckedNickname.postValue(response.body()!!.isDuplicated)
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
                    _isCheckedEmail.postValue(response.body()!!.isDuplicated)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun checkPw(pw: String, token: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.userService.checkPw(pw, token)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _isCheckedPw.postValue(response.body()!!.output == 1)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }
}