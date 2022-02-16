package com.ssafy.near.src.main.handsign.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
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

        /* 여백, 너비에 대한 정의 */
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.page_margin) // dimens.xml 파일 안에 크기를 정의해두었다. (200dp)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.page_width) // dimens.xml 파일이 없으면 생성해야함 (50dp)
        val screenWidth = resources.displayMetrics.widthPixels // 스마트폰의 너비 길이를 가져옴
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.vpHandSign.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        binding.vpHandSign.offscreenPageLimit = 1 // 몇 개의 페이지를 미리 로드 해둘것인지
        binding.vpHandSign.adapter = CardAdapter(arrayOf(handSignInfo, handSignInfo)) // 어댑터 생성 (Animation꺼 재활용 했습니다)
        binding.vpHandSign.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
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
            true -> binding.ivBookmarkIcon.setImageResource(R.drawable.ic_my_note_bookmark)
            false -> binding.ivBookmarkIcon.setImageResource(R.drawable.ic_bookmark_add)
        }
    }
}