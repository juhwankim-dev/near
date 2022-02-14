package com.ssafy.near.src.main.game.wordquiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.databinding.ListItemGameRoomBinding
import com.ssafy.near.dto.RoomInfo

class RoomListAdapter : RecyclerView.Adapter<RoomListAdapter.RoomInfoHolder>() {
    interface ItemClickListener {
        fun onClick(roomInfo: RoomInfo)
    }

    private lateinit var itemClickListener: ItemClickListener
    var roomList = mutableListOf<RoomInfo>()

    inner class RoomInfoHolder(private val binding: ListItemGameRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(roomInfo: RoomInfo) {
            binding.rooomInfo = roomInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomInfoHolder {
        val listItemBinding = ListItemGameRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomInfoHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: RoomInfoHolder, position: Int) {
        holder.apply {
            bindInfo(roomList[position])

            itemView.setOnClickListener {
                itemClickListener.onClick(roomList[position])
            }
        }
    }

    override fun getItemCount(): Int = roomList.size

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}