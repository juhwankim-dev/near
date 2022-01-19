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
}