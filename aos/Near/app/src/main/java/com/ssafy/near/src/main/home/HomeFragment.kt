package com.ssafy.near.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private var timer = Timer()
    val introduceStr = arrayOf("당신의 곁에", "N:ear")

    private val swipe: LottieAnimationView by lazy{requireActivity().findViewById(R.id.lottieView_swipe)}
    private val bottomSheet: ConstraintLayout by lazy{requireActivity().findViewById(R.id.layout_bottom_sheet)}
    lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        sheetBehavior= BottomSheetBehavior.from(bottomSheet)
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState) {
                    BottomSheetBehavior.STATE_COLLAPSED-> {
                        swipe.visibility = View.VISIBLE
                        binding.tvTypingSmallText.visibility = View.INVISIBLE
                        binding.tvTypingLargeText.visibility = View.VISIBLE
                    }
                    BottomSheetBehavior.STATE_DRAGGING-> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED-> {
                        swipe.visibility = View.GONE
                        binding.tvTypingSmallText.visibility = View.VISIBLE
                        binding.tvTypingLargeText.visibility = View.INVISIBLE
                    }
                    BottomSheetBehavior.STATE_HIDDEN-> {
                    }
                    BottomSheetBehavior.STATE_SETTLING-> {
                    }
                }
            }
        })
    }

    private fun initView() {
        timer.schedule(object: TimerTask() {
            var newStr = StringBuilder()
            var cnt = 0
            var index = 0
            var sequence = 0
            override fun run() {
                sequence = if((cnt / 2) % 2 == 0) 0 else 1

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
                    binding.tvTypingSmallText.text = newStr.toString()
                    binding.tvTypingLargeText.text = newStr.toString()
                }
            }
        }, 0, 300)
    }

    override fun onDestroy() {
        super.onDestroy()

        timer.cancel()
    }
}