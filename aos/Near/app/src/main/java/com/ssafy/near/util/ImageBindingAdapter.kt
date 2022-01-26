package com.ssafy.near.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ssafy.near.R

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("bindImageUrl")
    fun bindImageUrl(view: ImageView, src: String){
        Glide.with(view.context)
            .load(src)
            .error(R.drawable.img_thumbnail_sample_2)
            .into(view)
    }
}