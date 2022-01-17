package com.ssafy.near.src

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}