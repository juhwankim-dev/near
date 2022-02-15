package com.ssafy.near.src.main.game.wordquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentWaitingRoomBinding
import com.ssafy.near.dto.RoomInfo
import com.ssafy.near.repository.GameRepository


class WaitingRoomFragment : BaseFragment<FragmentWaitingRoomBinding>(R.layout.fragment_waiting_room) {
    private lateinit var roomInfo: RoomInfo
    private lateinit var wordQuizVm: WordQuizViewModel
    private lateinit var userListAdapter: UserListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            roomInfo = it.getSerializable("roomInfo") as RoomInfo
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        initEvent()
    }

    private fun initViewModel() {
        wordQuizVm = ViewModelProvider(requireActivity(), WordQuizViewModelFactory(GameRepository()))
            .get(WordQuizViewModel::class.java)

        wordQuizVm.apply {
            connectSocket()
            connect(roomInfo.roomId)
            sendMessage(roomInfo.roomId)
        }

        wordQuizVm.getMessage().observe(viewLifecycleOwner) {
            userListAdapter.apply {
                userList.add(it.sender)
                notifyDataSetChanged()
            }
            showToastMessage(it.message)
        }
    }

    private fun initView() {
        binding.roomInfo = roomInfo

        userListAdapter = UserListAdapter()
        binding.gvWaitingUser.apply {
            adapter = userListAdapter
        }
    }

    private fun initEvent() {
        binding.ivExitGame.setOnClickListener {
            requireActivity().finish()
        }

        binding.btnPlayGame.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_word_quiz, WordQuizFragment())
                .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(roomInfo: RoomInfo) =
            WaitingRoomFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("roomInfo", roomInfo)
                }
            }
    }
}