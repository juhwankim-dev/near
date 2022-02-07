package com.ssafy.near.src.main.mynote

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityMyNoteBinding
import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.repository.HandSignRepository
import com.ssafy.near.src.main.handsign.HandSignDetailActivity
import com.ssafy.near.src.main.handsign.HandSignViewModel
import com.ssafy.near.src.main.handsign.HandSignViewModelFactory

class MyNoteActivity : BaseActivity<ActivityMyNoteBinding>(R.layout.activity_my_note) {
    private lateinit var handSignViewModel: HandSignViewModel
    lateinit var myNoteAdapter: MyNoteAdapter
    private var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initView()
        initEvent()
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

        handSignViewModel.loadBookmarkList(sSharedPreferences.getUserId())

        var spinnerAdapter = ArrayAdapter(this, R.layout.list_item_spinner, R.id.tv_spinner_item, arrayOf("최근 등록순", "가나다 순"))
        spinnerAdapter.setDropDownViewResource(R.layout.list_item_spinner)
        binding.spinner.adapter = spinnerAdapter
    }

    private fun initEvent() {
        binding.ivAllCheck.setOnClickListener {
            myNoteAdapter.selectAll()
            updateCheckState(binding.ivAllCheck)
        }

        binding.btnDelete.setOnClickListener {
            var newList = ArrayList<HandSignInfo>()
            myNoteAdapter.bookmarkList.forEach {
                when(it.isChecked) {
                    true -> handSignViewModel.deleteBookmark(it.handcontent_key.toString(), sSharedPreferences.getUserId())
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
                var intent = Intent(this@MyNoteActivity, HandSignDetailActivity::class.java)
                intent.putExtra("handSignInfo", handSignInfo)
                startActivity(intent)
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
                imageView.imageTintList = ColorStateList.valueOf(imageView.resources.getColor(R.color.temp_blue))
                isChecked = true
            }
        }
    }
}