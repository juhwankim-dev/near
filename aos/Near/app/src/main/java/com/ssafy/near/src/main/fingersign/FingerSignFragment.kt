package com.ssafy.near.src.main.fingersign

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
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

        var spinnerAdapter = ArrayAdapter(requireContext(), R.layout.list_item_spinner, R.id.tv_spinner_item, arrayOf("오름차순", "내림차순"))
        spinnerAdapter.setDropDownViewResource(R.layout.list_item_spinner)
        binding.spinner.adapter = spinnerAdapter
    }

    private fun initViewModel() {
        fingerSignViewModel = ViewModelProvider(requireActivity(), FingerSignViewModelFactory(FingerSignRepository()))
            .get(FingerSignViewModel::class.java)

        fingerSignViewModel.getFingerSignList().observe(viewLifecycleOwner) {
            fingerSignAdapter.setInitList(it)
        }
    }

    private fun initEvent() {
        fingerSignAdapter.setItemClickListener(object : FingerSignAdapter.ItemClickListener{
            override fun onClick(fingerSignInfo: FingerSignInfo) {
                fingerSignDialog.createDialog(fingerSignInfo)
            }
        })

        binding.tlFingerSign.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position) {
                    0 -> fingerSignAdapter.filter?.filter("9")
                    1 -> fingerSignAdapter.filter?.filter("0")
                    2 -> fingerSignAdapter.filter?.filter("1")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}