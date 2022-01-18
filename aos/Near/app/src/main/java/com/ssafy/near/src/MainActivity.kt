package com.ssafy.near.src

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityMainBinding
import com.ssafy.near.repository.SampleRepository

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    lateinit var sampleViewModel: SampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sampleRepository = SampleRepository()
        sampleViewModel = ViewModelProvider(this, SampleViewModelFactory(sampleRepository)).get(SampleViewModel::class.java)

        initEvent()
    }

    private fun initEvent() {

        // 버튼 클릭시 HTTP 요청하는 샘플
//        binding.XXX.setOnClickListener {
//            sampleViewModel.selectselectSamples(1)
//        }

        // 응답받은 데이터를 LiveData가 Observe 한다.
        // it에 데이터가 저장된다. 원하는 뷰에 뿌려주면 끝
//        sampleViewModel.getSampleResponse().observe(this, {
//            it.items
//        })

        setContentView(R.layout.activity_main)

        // 네비게이션 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        // 네비게이션 컨트롤러
        val navController = navHostFragment.navController

        // 바인딩
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }
}