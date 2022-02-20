package com.ssafy.near.src.main.game.wordquiz.playing

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentWordResultBinding
import com.ssafy.near.dto.GameUser
import com.ssafy.near.dto.Result
import com.ssafy.near.dto.RoomInfo
import com.ssafy.near.repository.GameRepository
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModel
import com.ssafy.near.src.main.game.wordquiz.WordQuizViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WordResultFragment : BaseFragment<FragmentWordResultBinding>(R.layout.fragment_word_result) {
    lateinit var wordResultAdapter: WordResultAdapter
    lateinit var roomInfo: RoomInfo
    lateinit var userList: ArrayList<GameUser>

    private val wordQuizViewModel: WordQuizViewModel by lazy {
        ViewModelProvider(requireActivity(), WordQuizViewModelFactory(GameRepository()))
            .get(WordQuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            roomInfo = it.getSerializable("roomInfo") as RoomInfo
            userList = it.getStringArrayList("userList") as ArrayList<GameUser>
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        var list = mutableListOf<Result>()
        var map = wordQuizViewModel.getScoreMap()
        map.forEach { (name, score) ->
            list.add(Result(name, score.toString()))
        }

        list.sortByDescending { it.score }

        wordResultAdapter = WordResultAdapter(list, userList)
        binding.rvResult.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wordResultAdapter
        }

        CoroutineScope(Dispatchers.Main).launch {
            showToastMessage("5초 뒤 방목록으로 이동합니다.")
            delay(5000)
            (context as WordQuizActivity).exitRoom()
        }
    }

    private fun initEvent() {
        binding.btnExit.setOnClickListener {
            wordQuizViewModel.deleteRoom(roomInfo.roomId)
            (context as WordQuizActivity).exitRoom()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(roomInfo: RoomInfo,  userList: ArrayList<GameUser>) =
            WordResultFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("roomInfo", roomInfo)
                    putSerializable("userList", userList)
                }
            }
    }
}