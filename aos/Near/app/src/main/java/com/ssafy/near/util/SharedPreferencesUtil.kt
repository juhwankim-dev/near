package com.ssafy.near.util

import android.content.Context
import android.content.SharedPreferences
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.dto.UserToken

class SharedPreferencesUtil(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(ApplicationClass.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun addUser(userToken: UserToken) {
        val editor = preferences.edit()
        editor.putInt("id", userToken.id)
        editor.putString("token", userToken.token)
        editor.apply()
    }

    // TODO: 2022-01-20 우선 이 id를 가져오는거로 작성해놓았지만 회원 id 가져오는거로 변경 필요
    fun getUserId(): Int {
        return preferences.getInt("id", -1)
    }

    fun getUserToken(): String {
        return preferences.getString("token", "default")!!
    }

    fun deleteUser() {
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}