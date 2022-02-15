package com.ssafy.near.src.main.game.wordquiz.playing

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentWaitingRoomBinding
import com.ssafy.near.dto.MsgType
import com.ssafy.near.dto.RoomInfo
import com.ssafy.near.repository.GameRepository
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModel
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModelFactory
import com.ssafy.near.util.SharedPreferencesUtil.Companion.DEFAULT_TOKEN


class WaitingRoomFragment : BaseFragment<FragmentWaitingRoomBinding>(R.layout.fragment_waiting_room) {
    private val wordQuizViewModel: WordQuizViewModel by lazy {
        ViewModelProvider(requireActivity(), WordQuizViewModelFactory(GameRepository()))
            .get(WordQuizViewModel::class.java)
    }

    private lateinit var roomInfo: RoomInfo
    private lateinit var nickname: String
    private lateinit var userListAdapter: UserListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            roomInfo = it.getSerializable("roomInfo") as RoomInfo
        }
        nickname = sSharedPreferences.getNickname()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        initEvent()
    }

    private fun initViewModel() {
        wordQuizViewModel.getMessage().observe(viewLifecycleOwner) {
            when (it.type) {
                MsgType.ENTER -> {
                    userListAdapter.apply {
                        if (userList.contains(it.sender) == false){
                            userList.add(it.sender)
                            notifyDataSetChanged()
                        }
                    }
                    showToastMessage(it.message)
                }
                MsgType.START -> {
                    val userList = ArrayList<String>()
                    userListAdapter.userList.forEach { name -> userList.add(name) }
                    (context as WordQuizActivity)
                        .onChangeFragment(WordQuizFragment.newInstance(roomInfo, userList, nickname))
                }
                MsgType.OUT -> {
                    if (it.sender == roomInfo.host) {
                        showToastMessage("방장이 퇴장하였습니다.")
                        requireActivity().finish()
                    } else {
                        userListAdapter.apply {
                            userList.remove(it.sender)
                            notifyDataSetChanged()
                        }
                        showToastMessage(it.message)
                    }
                }
            }
        }
    }

    private fun initView() {
        if (sSharedPreferences.getUserToken() == DEFAULT_TOKEN) {
            showToastMessage("로그인이 필요한 서비스입니다.")
            requireActivity().finish()
        }

        binding.roomInfo = roomInfo

        if (nickname == roomInfo.host) {
            binding.btnPlayGame.visibility = View.VISIBLE
        } else {
            binding.btnPlayGame.visibility = View.GONE
        }
        val list = mutableListOf(nickname)
        list.addAll(roomInfo.userList)
        userListAdapter = UserListAdapter(list)
        binding.gvWaitingUser.apply {
            adapter = userListAdapter
        }
    }

    private fun initEvent() {
        binding.ivExitGame.setOnClickListener {
            (context as WordQuizActivity).exitRoom()
        }

        binding.btnPlayGame.setOnClickListener {
            wordQuizViewModel.sendMessage(MsgType.START, roomInfo.roomId, roomInfo.host, "")
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