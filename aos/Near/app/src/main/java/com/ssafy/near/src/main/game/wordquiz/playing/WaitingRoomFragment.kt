package com.ssafy.near.src.main.game.wordquiz.playing

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentWaitingRoomBinding
import com.ssafy.near.dto.RoomInfo
import com.ssafy.near.repository.GameRepository
import com.ssafy.near.repository.UserRepository
import com.ssafy.near.src.UserViewModel
import com.ssafy.near.src.UserViewModelFactory
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModel
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModelFactory
import com.ssafy.near.util.SharedPreferencesUtil.Companion.DEFAULT_TOKEN


class WaitingRoomFragment : BaseFragment<FragmentWaitingRoomBinding>(R.layout.fragment_waiting_room) {
    private lateinit var roomInfo: RoomInfo
    private lateinit var nickname: String
    private lateinit var userViewModel: UserViewModel
    private lateinit var wordQuizViewModel: WordQuizViewModel
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
        userViewModel = ViewModelProvider(requireActivity(), UserViewModelFactory(UserRepository()))
            .get(UserViewModel::class.java)
        wordQuizViewModel = ViewModelProvider(requireActivity(), WordQuizViewModelFactory(GameRepository()))
            .get(WordQuizViewModel::class.java)

        wordQuizViewModel.apply {
            connectSocket()
            connect(roomInfo.roomId)
        }

        userViewModel.getUserInfo().observe(viewLifecycleOwner) {
            nickname = it!!.nickname
            wordQuizViewModel.sendEntrance(roomInfo.roomId, it.nickname)
        }

        wordQuizViewModel.getMessage().observe(viewLifecycleOwner) {
            userListAdapter.apply {
                userList.add(it.sender)
                notifyDataSetChanged()
            }
            showToastMessage(it.message)
        }
    }

    private fun initView() {
        if (sSharedPreferences.getUserToken() == DEFAULT_TOKEN) {
            showToastMessage("로그인이 필요한 서비스입니다.")
            requireActivity().finish()
        }

        userViewModel.loadUserInfo(sSharedPreferences.getUserToken())
        binding.roomInfo = roomInfo

        userListAdapter = UserListAdapter()
        binding.gvWaitingUser.apply {
            adapter = userListAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        wordQuizViewModel.disconnect()
    }

    private fun initEvent() {
        binding.ivExitGame.setOnClickListener {
            requireActivity().finish()
        }

        binding.btnPlayGame.setOnClickListener {
            val userList = ArrayList<String>()
            userListAdapter.userList.forEach { userList.add(it) }

//            (context as WordQuizActivity).onChangeFragment(WordQuizFragment.newInstance(roomInfo, userList, nickname))
            (context as WordQuizActivity).onChangeFragment(WordQuizFragment())

        }

        binding.ivSetting.setOnClickListener {
            var avatarDialog = AvatarDialog(requireContext())
            avatarDialog.createDialog()
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