package com.ssafy.near.src.main.handsign

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentHandSignBinding
import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.lib.DraggablePanel
import com.ssafy.near.repository.HandSignRepository
import com.ssafy.near.src.main.handsign.detail.BottomFragment
import com.ssafy.near.src.main.handsign.detail.TopFragment

class HandSignFragment : BaseFragment<FragmentHandSignBinding>(R.layout.fragment_hand_sign) {
    lateinit var handSignViewModel: HandSignViewModel
    lateinit var handSignAdapter: HandSignAdapter
    lateinit var myNoteAdapter: MyNoteAdapter
    private var isChecked = false

    lateinit var shadow: View
    lateinit var topFragment: TopFragment
    lateinit var bottomFragment: BottomFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        initDraggablePanel()
        initEvent()
    }

    private fun initViewModel() {
        handSignViewModel = ViewModelProvider(requireActivity(), HandSignViewModelFactory(HandSignRepository()))
            .get(HandSignViewModel::class.java)

        handSignViewModel.getHandSignList().observe(viewLifecycleOwner) {
            handSignAdapter.setInitList(it)
        }

        handSignViewModel.getBookmarkList().observe(viewLifecycleOwner) {
            myNoteAdapter.updateList(it)
        }
    }

    private fun initView() {
        handSignAdapter = HandSignAdapter()
        myNoteAdapter = MyNoteAdapter()
        binding.rvHandSign.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = handSignAdapter
        }

        handSignViewModel.loadHandSignList()
        handSignViewModel.loadBookmarkList(ApplicationClass.sSharedPreferences.getUserId())

        var spinnerAdapter = ArrayAdapter(requireContext(), R.layout.list_item_spinner, R.id.tv_spinner_item, arrayOf("오름차순", "내림차순"))
        spinnerAdapter.setDropDownViewResource(R.layout.list_item_spinner)
        binding.spinner.adapter = spinnerAdapter
    }

    private fun initDraggablePanel() {
        shadow = requireActivity().findViewById(R.id.shadow)

        binding.alpha.visibility = View.GONE
        binding.draggablePanel.visibility = View.GONE

        binding.draggablePanel.setDraggableListener(object : DraggablePanel.DraggableListener {
            override fun onChangeState(state: DraggablePanel.State) {
            }

            override fun onChangePercent(percent: Float) {
                binding.alpha.alpha = 1 - percent
                shadow.alpha = percent
            }

        })

        binding.draggablePanel.mWidthWhenMiddle = 200
    }

    private fun initEvent() {
        binding.tlHandSign.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position) {
                    0 -> {
                        binding.rvHandSign.adapter = handSignAdapter
                        binding.ivAllCheck.visibility = View.GONE
                        binding.tvAllCheck.visibility = View.GONE
                        binding.btnDelete.visibility = View.GONE
                    }
                    1 -> {
                        binding.rvHandSign.adapter = myNoteAdapter
                        binding.ivAllCheck.visibility = View.VISIBLE
                        binding.tvAllCheck.visibility = View.VISIBLE
                        binding.btnDelete.visibility = View.VISIBLE
                        handSignViewModel.loadBookmarkList(ApplicationClass.sSharedPreferences.getUserId())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        handSignAdapter.setItemClickListener(object : HandSignAdapter.ItemClickListener{
            override fun onClick(handSignInfo: HandSignInfo) {
                handSignViewModel.selectedHandSignInfo = handSignInfo
                binding.draggablePanel.maximize()
                openDetailPage()
            }
        })

        binding.ivAllCheck.setOnClickListener {
            myNoteAdapter.selectAll()
            updateCheckState(binding.ivAllCheck)
        }

        binding.btnDelete.setOnClickListener {
            var newList = ArrayList<HandSignInfo>()
            myNoteAdapter.bookmarkList.forEach {
                when(it.isChecked) {
                    true -> handSignViewModel.deleteBookmark(it.handcontent_key.toString(), ApplicationClass.sSharedPreferences.getUserId())
                    false -> newList.add(it)
                }
            }
            myNoteAdapter.updateList(newList)
        }

        myNoteAdapter.setHasUncheckedListener(object : MyNoteAdapter.HasUncheckedListener{
            override fun onClick(hasUnchecked: Boolean) {
                isChecked = when(hasUnchecked) {
                    true -> true
                    false -> false
                }
                updateCheckState(binding.ivAllCheck)
            }
        })

        myNoteAdapter.setItemClickListener(object : MyNoteAdapter.ItemClickListener{
            override fun onClick(handSignInfo: HandSignInfo) {
                handSignViewModel.selectedHandSignInfo = handSignInfo
                binding.draggablePanel.maximize()
                openDetailPage()
            }
        })
    }

    private fun updateCheckState(imageView: ImageView) {
        when(isChecked) {
            true -> {
                imageView.imageTintList = ColorStateList.valueOf(imageView.resources.getColor(R.color.gray_unchecked))
                isChecked = false
            }

            false -> {
                imageView.imageTintList = ColorStateList.valueOf(imageView.resources.getColor(R.color.main_color))
                isChecked = true
            }
        }
    }

    private fun openDetailPage() {
        binding.alpha.visibility = View.VISIBLE
        binding.draggablePanel.visibility = View.VISIBLE

        topFragment = TopFragment()
        bottomFragment = BottomFragment()

        childFragmentManager.beginTransaction().replace(R.id.frameTop, topFragment).commit()
        childFragmentManager.beginTransaction().replace(R.id.frameBottom, bottomFragment).commit()
    }

    fun removeFragment() {
        binding.draggablePanel.close()
        childFragmentManager.beginTransaction()
            .remove(bottomFragment)
            .commit()

        childFragmentManager.beginTransaction()
            .remove(topFragment)
            .commit()
    }
}