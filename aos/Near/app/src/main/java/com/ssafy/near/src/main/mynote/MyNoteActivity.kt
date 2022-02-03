package com.ssafy.near.src.main.mynote

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityMyNoteBinding
import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.repository.HandSignRepository
import com.ssafy.near.src.main.handsign.HandSignAdapter
import com.ssafy.near.src.main.handsign.HandSignViewModel
import com.ssafy.near.src.main.handsign.HandSignViewModelFactory

class MyNoteActivity : BaseActivity<ActivityMyNoteBinding>(R.layout.activity_my_note) {
    private lateinit var handSignViewModel: HandSignViewModel
    lateinit var myNoteAdapter: MyNoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initView()
    }

    private fun initViewModel() {
        handSignViewModel = ViewModelProvider(this, HandSignViewModelFactory(HandSignRepository()))
            .get(HandSignViewModel::class.java)

        handSignViewModel.getbookmarkList().observe(this) {
            myNoteAdapter.updateList(it)
        }
    }

    private fun initView() {
        myNoteAdapter = MyNoteAdapter()
        binding.rvMyNote.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myNoteAdapter
        }

        handSignViewModel.loadBookmarkList("${sSharedPreferences.getUserId()}")

        var spinnerAdapter = ArrayAdapter(this, R.layout.list_item_spinner, R.id.tv_spinner_item, arrayOf("최근 등록순", "가나다 순"))
        spinnerAdapter.setDropDownViewResource(R.layout.list_item_spinner)
        binding.spinner.adapter = spinnerAdapter
    }
}