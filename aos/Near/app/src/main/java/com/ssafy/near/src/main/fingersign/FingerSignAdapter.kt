package com.ssafy.near.src.main.fingersign

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.databinding.ListItemFingerSignBinding

class FingerSignAdapter : RecyclerView.Adapter<FingerSignAdapter.FingerSignViewHolder>() {
    var repoList = mutableListOf<String>()

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
        holder.bindInfo(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size
}