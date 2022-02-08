package com.ssafy.near.src.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    lateinit var progressAdapter: ProgressAdapter
    private var timer = Timer()
    val introduceStr = arrayOf("당신의 곁에", "N:ear")

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

//        binding.vpContent.also {
//            it.offscreenPageLimit = 1
//            it.adapter = ContentAdapter(mutableListOf("test", "test", "test", "test"))
//            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//            it.setPageTransformer { page, position ->
//                page.translationX = position * -offsetPx
//            }
//        }

//        binding.rvProgress.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = ProgressAdapter(mutableListOf("test", "test", "test", "test"))
//        }

//        var newStr = StringBuilder()
//        var cnt = 0
//        var index = 0
//        var sequence = 0
//
//        while(true) {
//            sequence = if((cnt / 2) % 2 == 0) {
//                0
//            } else {
//                1
//            }
//
//            var fullStr = introduceStr[sequence]
//            when(cnt % 2) {
//                0 -> {
//                    newStr.append(fullStr[index++])
//                    if(index == fullStr.length) {
//                        cnt++
//                        index--
//                    }
//                }
//                1 -> {
//                    newStr.deleteCharAt(index--)
//                    if(index < 0) {
//                        cnt++
//                        index = 0
//                    }
//                }
//            }
//
//            binding.tvTypingText.text = newStr.toString()
//        }

        timer.schedule(object: TimerTask() {
            var newStr = StringBuilder()
            var cnt = 0
            var index = 0
            var sequence = 0
            override fun run() {
                sequence = if((cnt / 2) % 2 == 0) {
                    0
                } else {
                    1
                }

                var fullStr = introduceStr[sequence]
                when(cnt % 2) {
                    0 -> {
                        newStr.append(fullStr[index++])
                        if(index == fullStr.length) {
                            cnt++
                            index--
                        }
                    }
                    1 -> {
                        newStr.deleteCharAt(index--)
                        if(index < 0) {
                            cnt++
                            index = 0
                        }
                    }
                }

                requireActivity().runOnUiThread {
                    binding.tvTypingText.text = newStr.toString()
                }
            }
        }, 0, 300)
    }

    override fun onDestroy() {
        super.onDestroy()

        timer.cancel()
    }
}