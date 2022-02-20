package com.ssafy.near.src.main.game.wordquiz.room

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentRoomListBinding
import com.ssafy.near.dto.RoomInfo
import com.ssafy.near.repository.GameRepository
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModel
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModelFactory


class RoomListFragment : BaseFragment<FragmentRoomListBinding>(R.layout.fragment_room_list) {
    private val wordQuizViewModel: WordQuizViewModel by lazy {
        ViewModelProvider(requireActivity(), WordQuizViewModelFactory(GameRepository()))
            .get(WordQuizViewModel::class.java)
    }
    private lateinit var roomListAdapter: RoomListAdapter


    override fun onResume() {
        super.onResume()
        initView()
        initViewModel()
        initEvent()
    }
    
    private fun initViewModel() {
        wordQuizViewModel.getRoomList().observe(viewLifecycleOwner) {
            roomListAdapter.updateList(it)
        }

        wordQuizViewModel.getRoomInfo().observe(viewLifecycleOwner) {
            when {
                it == null -> {
                    showToastMessage("존재하지 않는 방입니다.")
                    wordQuizViewModel.loadRooms()
                }
                it.userCount >= 4 -> {
                    showToastMessage("정원초과")
                    wordQuizViewModel.loadRooms()
                }
                else -> {
                    (context as RoomActivity).enterRoom(it)
                }
            }
        }
    }

    private fun initView() {
        roomListAdapter = RoomListAdapter()
        binding.rvRoomList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = roomListAdapter
        }

        wordQuizViewModel.loadRooms()
    }

    private fun initEvent() {
        binding.ivExitGame.setOnClickListener {
            requireActivity().finish()
        }

        binding.ivRefresh.setOnClickListener {
            wordQuizViewModel.loadRooms()
        }

        binding.fbCreate.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_room, CreatingRoomFragment())
                .addToBackStack(null)
                .commit()
        }

        roomListAdapter.setItemClickListener(object : RoomListAdapter.ItemClickListener {
            override fun onClick(roomInfo: RoomInfo) {
                wordQuizViewModel.loadRoom(roomInfo.roomId)
            }
        })
    }
}