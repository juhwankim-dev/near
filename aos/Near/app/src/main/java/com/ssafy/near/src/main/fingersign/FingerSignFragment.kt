package com.ssafy.near.src.main.fingersign

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentFingerSignBinding
import com.ssafy.near.dto.FingerSignInfo
import com.ssafy.near.repository.FingerSignRepository

class FingerSignFragment : BaseFragment<FragmentFingerSignBinding>(R.layout.fragment_finger_sign) {
    private lateinit var fingerSignViewModel: FingerSignViewModel
    lateinit var fingerSignAdapter: FingerSignAdapter
    lateinit var fingerSignDialog: FingerSignDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        initEvent()
    }

    private fun initView() {
        fingerSignAdapter = FingerSignAdapter()
        binding.rvFingerSign.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fingerSignAdapter
        }

        fingerSignDialog = FingerSignDialog(requireContext())

        fingerSignViewModel.loadFingerSignList()
    }

    private fun initViewModel() {
        fingerSignViewModel = ViewModelProvider(requireActivity(), FingerSignViewModelFactory(FingerSignRepository()))
            .get(FingerSignViewModel::class.java)

        fingerSignViewModel.getFingerSignList().observe(viewLifecycleOwner, {
            fingerSignAdapter.setInitList(it)
        })
    }

    private fun initEvent() {
        fingerSignAdapter.setItemClickListener(object : FingerSignAdapter.ItemClickListener{
            override fun onClick(fingerSignInfo: FingerSignInfo) {
                fingerSignDialog.createDialog(fingerSignInfo)
            }
        })

        binding.rbAllFingerSign.setOnClickListener {
            fingerSignAdapter.filter?.filter("-1")
        }

        binding.rbConsonant.setOnClickListener {
            fingerSignAdapter.filter?.filter("0")
        }

        binding.rbVowel.setOnClickListener {
            fingerSignAdapter.filter?.filter("1")
        }
    }
}