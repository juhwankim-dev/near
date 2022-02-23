package com.ssafy.near.src.main.game.wordquiz.playing

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.databinding.ListItemChatBinding
import com.ssafy.near.dto.Message
import com.ssafy.near.dto.MsgType

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    var list = ArrayList<Message>()

    inner class ChatViewHolder(private val binding: ListItemChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(msg: Message) {
            binding.msg = msg
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        var listItemChatBinding = ListItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(listItemChatBinding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bindInfo(list[position]!!)
    }

    override fun getItemCount(): Int = list.size

    fun addStartMessage() {
        list.add(Message(MsgType.END, "", "", ""))
        notifyDataSetChanged()
    }

    fun updateNewMsg(msg: Message) {
        list.add(msg)
        notifyItemInserted(list.lastIndex)
    }
}