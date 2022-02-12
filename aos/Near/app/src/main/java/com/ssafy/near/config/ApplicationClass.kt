package com.ssafy.near.config

import android.app.Application
import com.ssafy.near.util.SharedPreferencesUtil
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationClass : Application() {
    private val baseUrl = "https://hoonycode2.loca.lt/"
    companion object {
        lateinit var sSharedPreferences: SharedPreferencesUtil
        const val SHARED_PREFERENCES_NAME = "SSAFY_APP"

        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()

        sSharedPreferences = SharedPreferencesUtil(applicationContext)

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}