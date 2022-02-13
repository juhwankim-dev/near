package com.ssafy.near.src.main.handsign.detail

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.ssafy.near.lib.DraggablePanel
import com.ssafy.near.lib.utils.inflate
import com.ssafy.near.lib.utils.reWidth
import com.ssafy.near.R

class DraggableSource @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : DraggablePanel(context, attrs, defStyleAttr) {

    var mWidthWhenMax = 0

    var mWidthWhenMiddle = 0

    var mWidthWhenMin = 0

    init {
        getFrameFirst().addView(inflate(R.layout.layout_top))
        getFrameSecond().addView(inflate(R.layout.layout_bottom))
    }

    override fun initFrame() {
        mWidthWhenMax = width

        mWidthWhenMiddle = (width - mPercentWhenMiddle * mMarginEdgeWhenMin).toInt()

        mWidthWhenMin = mHeightWhenMinDefault * 22 / 9

        super.initFrame()
    }

    override fun refreshFrameFirst() {
        super.refreshFrameFirst()

        val width = if (mCurrentPercent < mPercentWhenMiddle) {
            (mWidthWhenMax - (mWidthWhenMax - mWidthWhenMiddle) * mCurrentPercent)
        } else {
            (mWidthWhenMiddle - (mWidthWhenMiddle - mWidthWhenMin) * (mCurrentPercent - mPercentWhenMiddle) / (1 - mPercentWhenMiddle))
        }

        var frameTop = findViewById<FrameLayout>(R.id.frameTop)

        frameTop.reWidth(width.toInt())
    }
}