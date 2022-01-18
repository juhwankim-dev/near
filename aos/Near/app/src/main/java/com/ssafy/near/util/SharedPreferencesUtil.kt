package com.ssafy.near.util

import android.content.Context
import android.content.SharedPreferences
import com.ssafy.near.config.ApplicationClass

class SharedPreferencesUtil(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(ApplicationClass.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun addUser(token: String) {
        val editor = preferences.edit()
        editor.putString("user", token)
        editor.apply()
    }
}