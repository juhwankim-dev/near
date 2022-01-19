package com.ssafy.near.src.main.fingersign

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.databinding.ListItemFingerSignBinding

class FingerSignAdapter : RecyclerView.Adapter<FingerSignAdapter.FingerSignViewHolder>() {
    private lateinit var itemClickListner: ItemClickListener
    var list = mutableListOf<String>()

    inner class FingerSignViewHolder(private val binding: ListItemFingerSignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(item: String) {
            //binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FingerSignViewHolder {
        var listItemBinding = ListItemFingerSignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FingerSignViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: FingerSignViewHolder, position: Int) {
        holder.apply {
            bindInfo(list[position])

            //클릭연결
            itemView.setOnClickListener{
                itemClickListner.onClick(list[position])
            }
        }
    }

    override fun getItemCount(): Int = list.size

    interface ItemClickListener {
        fun onClick(id: String)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }
}