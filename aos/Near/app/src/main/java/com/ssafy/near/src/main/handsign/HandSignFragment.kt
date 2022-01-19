package com.ssafy.near.src.main.handsign

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentHandSignBinding

class HandSignFragment : BaseFragment<FragmentHandSignBinding>(R.layout.fragment_hand_sign) {
    lateinit var handSignAdapter: HandSignAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        handSignAdapter = HandSignAdapter()
        binding.rvHandSign.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = handSignAdapter
        }
    }

    private fun initEvent() {
        handSignAdapter.setItemClickListener(object : HandSignAdapter.ItemClickListener{
            override fun onClick(o_id: Int) {
                // TODO 단어가 클릭되었을 때 할 동작
            }
        })
    }
}