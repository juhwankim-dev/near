package com.ssafy.near.util

import android.content.Context
import android.content.SharedPreferences
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.dto.UserToken

class SharedPreferencesUtil(context: Context) {
    companion object {
        const val DEFAULT_ID = "-1"
        const val DEFAULT_TOKEN = "default"
        const val DEFAULT_NICKNAME = "로그인이 필요합니다."
    }

    private var preferences: SharedPreferences =
        context.getSharedPreferences(ApplicationClass.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun addUser(userToken: UserToken) {
        val editor = preferences.edit()
        editor.putString("id", userToken.id.toString())
        editor.putString("token", userToken.token)
        editor.apply()
    }

    fun setNickname(nickname: String) {
        val editor = preferences.edit()
        editor.putString("nickName", nickname)
        editor.apply()
    }

    fun getUserId(): String {
        return preferences.getString("id", DEFAULT_ID)!!
    }

    fun getUserToken(): String {
        return preferences.getString("token", DEFAULT_TOKEN)!!
    }

    fun getNickname(): String {
        return preferences.getString("nickName", DEFAULT_NICKNAME)!!
    }

    fun deleteUser() {
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}