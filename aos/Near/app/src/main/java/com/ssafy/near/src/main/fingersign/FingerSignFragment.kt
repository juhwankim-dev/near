package com.ssafy.near.src.main.fingersign

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentFingerSignBinding

class FingerSignFragment : BaseFragment<FragmentFingerSignBinding>(R.layout.fragment_finger_sign) {
    lateinit var fingerSignAdapter: FingerSignAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        fingerSignAdapter = FingerSignAdapter()
        binding.rvFingerSign.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fingerSignAdapter
        }
    }

    private fun initEvent() {
        // 버튼 클릭시 HTTP 요청하는 샘플
//        binding.XXX.setOnClickListener {
//            sampleViewModel.selectselectSamples(1)
//        }

//         응답받은 데이터를 LiveData가 Observe 한다.
//         it에 데이터가 저장된다. 원하는 뷰에 뿌려주면 끝
//        sampleViewModel.getSampleResponse().observe(this, {
//            it.items
//        })
    }
}