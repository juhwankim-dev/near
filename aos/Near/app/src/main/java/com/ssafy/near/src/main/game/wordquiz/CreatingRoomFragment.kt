package com.ssafy.near.src.main.game.wordquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentCreatingRoomBinding
import com.ssafy.near.repository.GameRepository


class CreatingRoomFragment : BaseFragment<FragmentCreatingRoomBinding>(R.layout.fragment_creating_room) {
    private lateinit var wordQuizViewModel: WordQuizViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVieModel()
        initEvent()
    }

    private fun initVieModel() {
        wordQuizViewModel = ViewModelProvider(requireActivity(), WordQuizViewModelFactory(
            GameRepository())).get(WordQuizViewModel::class.java)

        wordQuizViewModel.getRoomInfo().observe(viewLifecycleOwner) {
            val intent = Intent(requireActivity(), WordQuizActivity::class.java)
            intent.putExtra("roomId", it.roomId)
            startActivity(intent)
        }
    }

    private fun initEvent() {
        binding.btnSave.setOnClickListener {
            val roomName = binding.etRoomName.text.toString().trim()
            if (roomName != "") {
                wordQuizViewModel.createRoom(roomName)
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