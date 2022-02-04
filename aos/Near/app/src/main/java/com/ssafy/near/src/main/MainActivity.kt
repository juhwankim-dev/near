package com.ssafy.near.src.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initEvent()
    }

    private fun initEvent() {
        // 네비게이션 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        // 네비게이션 컨트롤러
        val navController = navHostFragment.navController

        // 바인딩
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }
}