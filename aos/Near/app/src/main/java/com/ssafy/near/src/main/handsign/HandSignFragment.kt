package com.ssafy.near.src.main.handsign

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentHandSignBinding
import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.repository.HandSignRepository
import com.ssafy.near.src.main.MainActivity

class HandSignFragment : BaseFragment<FragmentHandSignBinding>(R.layout.fragment_hand_sign) {
    private lateinit var handSignViewModel: HandSignViewModel
    lateinit var handSignAdapter: HandSignAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        initEvent()
    }

    private fun initViewModel() {
        handSignViewModel = ViewModelProvider(requireActivity(), HandSignViewModelFactory(HandSignRepository()))
            .get(HandSignViewModel::class.java)

        handSignViewModel.getHandSignList().observe(viewLifecycleOwner) {
            handSignAdapter.setInitList(it)
        }
    }

    private fun initView() {
        handSignAdapter = HandSignAdapter()
        binding.rvHandSign.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = handSignAdapter
        }

        handSignViewModel.loadHandSignList()
    }

    private fun initEvent() {
        handSignAdapter.setItemClickListener(object : HandSignAdapter.ItemClickListener{
            override fun onClick(handSignInfo: HandSignInfo) {
                val intent = Intent((context as MainActivity), HandSignDetailActivity::class.java)
                intent.putExtra("handSignInfo", handSignInfo)
                (context as MainActivity).startActivity(intent)
            }
        })
    }
}