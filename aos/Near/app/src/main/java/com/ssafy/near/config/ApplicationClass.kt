package com.ssafy.near.config

import android.app.Application
import com.ssafy.near.util.SharedPreferencesUtil

class ApplicationClass : Application() {

    companion object {
        lateinit var sSharedPreferences: SharedPreferencesUtil
        const val SHARED_PREFERENCES_NAME = "SSAFY_APP"
    }

    override fun onCreate() {
        super.onCreate()

        sSharedPreferences = SharedPreferencesUtil(applicationContext)
    }
}