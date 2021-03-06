package com.ssafy.near.src.main.game.wordquiz.room

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentCreatingRoomBinding
import com.ssafy.near.repository.GameRepository
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModel
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModelFactory


class CreatingRoomFragment : BaseFragment<FragmentCreatingRoomBinding>(R.layout.fragment_creating_room) {
    private lateinit var wordQuizViewModel: WordQuizViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initEvent()
    }

    private fun initViewModel() {
        wordQuizViewModel = ViewModelProvider(this, WordQuizViewModelFactory(
            GameRepository())).get(WordQuizViewModel::class.java)

        wordQuizViewModel.getRoomInfo().observe(viewLifecycleOwner) {
            (context as RoomActivity).enterRoom(it!!)
        }
    }

    private fun initEvent() {
        binding.btnSave.setOnClickListener {
            val roomName = binding.etRoomName.text.toString().trim()
            if (roomName != "") {
                wordQuizViewModel.createRoom(sSharedPreferences.getNickname(), roomName)
            }
        }

        binding.ivChange.setOnClickListener {
            binding.etRoomName.setFocusAndShowKeyboard(requireContext())
        }
    }

    fun EditText.setFocusAndShowKeyboard(context: Context) {
        this.requestFocus()
        setSelection(this.text.length)
        this.postDelayed({
            val inputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_FORCED)
        }, 100)
    }
}