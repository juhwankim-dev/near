package com.ssafy.near.src.main.game.wordquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
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

        binding.btnSave.setOnClickListener {
            val roomName = binding.etRoomName.text.toString().trim()
            if (roomName != "") {
                wordQuizViewModel.createRoom(roomName)
            }
        }
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
}