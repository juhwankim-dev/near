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
    lateinit var avatarDialog: AvatarDialog
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if(it.isEmpty) {
                showToastMessage("존재하지 않는 방입니다")
            } else {
                roomInfo = it.getSerializable("roomInfo") as RoomInfo
            }
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
                    var isNew = true
                    userListAdapter.apply {
                        userList.forEach { user ->
                            if (user.first == it.sender)
                                isNew = false
                        }
                        if (isNew) {
                            userList.add(Pair(it.sender, 0))
                            notifyDataSetChanged()
                            showToastMessage(it.message)
                        }
                    }
                }
                MsgType.START -> {
                    val userList = ArrayList<Pair<String, Int>>()
                    userListAdapter.userList.forEach { user -> userList.add(user) }
                    (context as WordQuizActivity)
                        .onChangeFragment(WordQuizFragment.newInstance(roomInfo, userList, nickname))
                }
                MsgType.OUT -> {
                    if (it.sender == roomInfo.host) {
                        showToastMessage("방장이 퇴장하였습니다.")
                        requireActivity().finish()
                    } else {
                        userListAdapter.apply {
                            userList.forEachIndexed { index, user ->
                                if (user.first == it.sender) {
                                    userList.removeAt(index)
                                }
                            }
                            notifyDataSetChanged()
                        }
                        showToastMessage(it.message)
                    }
                }
                MsgType.CHANGE -> {
                    userListAdapter.apply {
                        userList.forEachIndexed { index, user ->
                            if (user.first == it.sender) {
                                userList[index] = Pair(it.sender, it.message.toInt())
                            }
                        }
                        notifyDataSetChanged()
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

        val list = mutableListOf(Pair(nickname, 0))
        roomInfo.userList.forEach {
            list.add(Pair(it, 0))
        }
        userListAdapter = UserListAdapter(list)
        binding.gvWaitingUser.apply {
            adapter = userListAdapter
        }

        avatarDialog = AvatarDialog(requireContext())
    }

    private fun initEvent() {
        binding.ivExitGame.setOnClickListener {
            (context as WordQuizActivity).exitRoom()
        }

        binding.btnPlayGame.setOnClickListener {
            wordQuizViewModel.sendMessage(MsgType.START, roomInfo.roomId, roomInfo.host, "")
        }

        binding.ivSetting.setOnClickListener {
            avatarDialog.createDialog()
        }

        avatarDialog.setItemClickListener(object : AvatarDialog.ItemClickListener {
            override fun onClick(selectedAvatar: Int) {
                wordQuizViewModel.sendMessage(MsgType.CHANGE, roomInfo.roomId, nickname, selectedAvatar.toString())
            }
        })
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