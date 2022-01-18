package com.ssafy.near.src.main.home

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        /* 여백, 너비에 대한 정의 */
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.viewpager_page_margin) // dimens.xml 파일 안에 크기를 정의해두었다. (200dp)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.viewpage_page_width) // dimens.xml 파일이 없으면 생성해야함 (50dp)
        val screenWidth = resources.displayMetrics.widthPixels // 스마트폰의 너비 길이를 가져옴
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.vpContent.also {
            it.offscreenPageLimit = 1
            it.adapter = ContentAdapter(mutableListOf("test", "test", "test", "test"))
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            it.setPageTransformer { page, position ->
                page.translationX = position * -offsetPx
            }
        }
    }
}