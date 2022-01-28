package com.ssafy.near.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.databinding.ListItemHomeProgressBinding

class ProgressAdapter(var list: MutableList<String>) : RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder>() {

    inner class ProgressViewHolder(private val binding: ListItemHomeProgressBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(s: String) {
            // TODO data binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        var listItemBinding = ListItemHomeProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProgressViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
        holder.bindInfo(list[position])
    }

    override fun getItemCount(): Int = list.size

}