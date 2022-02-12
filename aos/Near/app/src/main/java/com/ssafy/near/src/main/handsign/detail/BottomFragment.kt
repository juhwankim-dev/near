package com.ssafy.near.src.main.handsign.detail

import android.os.Bundle
import android.view.View
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentBottomBinding
import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.src.main.handsign.HandSignFragment
import com.ssafy.near.src.main.handsign.HandSignViewModel

class BottomFragment : BaseFragment<FragmentBottomBinding>(R.layout.fragment_bottom) {
    private lateinit var handSignViewModel: HandSignViewModel
    private var isAddedWord = false
    private var handSignInfo: HandSignInfo? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        initEvent()
    }

    private fun initViewModel() {
        handSignViewModel = (parentFragment as HandSignFragment).handSignViewModel
        handSignInfo = handSignViewModel.selectedHandSignInfo

        handSignViewModel.getbookmarkList().observe(viewLifecycleOwner) {
            if(handSignInfo != null && it.contains(handSignInfo)) {
                isAddedWord = true
                updateBookmarkState()
            }
        }

        handSignViewModel.getAddBookmark().observe(viewLifecycleOwner) {
            showToastMessage("내 단어장에 추가하였습니다")
            isAddedWord = true
            updateBookmarkState()
        }

        handSignViewModel.getDeleteBookmark().observe(viewLifecycleOwner) {
            showToastMessage("내 단어장에서 삭제하였습니다")
            isAddedWord = false
            updateBookmarkState()
        }
    }

    private fun initView() {
        binding.handSignInfo = handSignInfo
        handSignViewModel.loadBookmarkList(ApplicationClass.sSharedPreferences.getUserId())
    }

    private fun initEvent() {
        binding.ivBookmark.setOnClickListener {
            when(handSignInfo) {
                null -> showToastMessage("데이터를 불러오지 못했습니다")
                else -> {
                    when(isAddedWord) {
                        true -> handSignViewModel.deleteBookmark(handSignInfo!!.handcontent_key.toString(), ApplicationClass.sSharedPreferences.getUserId())
                        false -> handSignViewModel.addBookmark(handSignInfo!!.handcontent_key.toString(), ApplicationClass.sSharedPreferences.getUserId())
                    }
                }
            }
        }
    }

    private fun updateBookmarkState() {
        when(isAddedWord) {
            true -> binding.ivBookmark.setImageResource(R.drawable.ic_my_note_bookmark)
            false -> binding.ivBookmark.setImageResource(R.drawable.ic_bookmark_add)
        }
    }
}