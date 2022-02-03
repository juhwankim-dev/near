package com.ssafy.near.src.main.mynote

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityMyNoteBinding
import com.ssafy.near.repository.HandSignRepository
import com.ssafy.near.src.main.handsign.HandSignViewModel
import com.ssafy.near.src.main.handsign.HandSignViewModelFactory

class MyNoteActivity : BaseActivity<ActivityMyNoteBinding>(R.layout.activity_my_note) {
    private lateinit var handSignViewModel: HandSignViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        handSignViewModel = ViewModelProvider(this, HandSignViewModelFactory(HandSignRepository()))
            .get(HandSignViewModel::class.java)

//        handSignViewModel.getHandSignList().observe(this) {
//            handSignAdapter.setInitList(it)
//        }
    }
}