package com.ssafy.near.src.main.game

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ssafy.near.R
import com.ssafy.near.databinding.CustomViewWordBinding

class CustomWordTextView : LinearLayout {
    private var binding: CustomViewWordBinding =
        CustomViewWordBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        getAttrs(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        getAttrs(attrs,defStyleAttr)
    }

    init {
        binding.ivWord.setImageResource(R.drawable.word_space)
    }

    private fun getAttrs(attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.WordTextView)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyle:Int){
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.WordTextView,defStyle,0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray : TypedArray){
        val word = typedArray.getText(R.styleable.WordTextView_text)
        binding.tvWord.text = word

        typedArray.recycle()
    }

    fun setText(word: String) {
        binding.tvWord.text = word
    }
}