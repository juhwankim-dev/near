package com.ssafy.near.src.main.handsign

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.databinding.ListItemHandSignBinding

class HandSignAdapter(var list: MutableList<String>) : RecyclerView.Adapter<HandSignAdapter.HandSignViewHolder>() {
    private lateinit var itemClickListner: ItemClickListener
    //var list = mutableListOf<String>()

    inner class HandSignViewHolder(private val binding: ListItemHandSignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(s: String) {
            binding.voca = s
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HandSignViewHolder {
        var listItemBinding = ListItemHandSignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HandSignViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: HandSignViewHolder, position: Int) {
        holder.apply {
            bindInfo(list[position])

            itemView.setOnClickListener {
                //itemClickListner.onClick(list[position].o_id) // TODO 나중에 BE 다 만들어지면 수정
            }
        }
    }

    // TODO 나중에 BE 다 만들어지면 수정
    fun updateList() {

    }

    override fun getItemCount(): Int = list.size

    //클릭 인터페이스 정의 사용하는 곳에서 만들어준다.
    interface ItemClickListener {
        fun onClick(id: Int)
    }

    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }
}