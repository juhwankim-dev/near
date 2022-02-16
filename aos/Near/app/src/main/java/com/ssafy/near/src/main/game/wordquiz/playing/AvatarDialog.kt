package com.ssafy.near.src.main.game.wordquiz.playing

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.near.R
import com.ssafy.near.databinding.DialogAvatarBinding

class AvatarDialog(context: Context) {
    private lateinit var itemClickListner: ItemClickListener
    private val context = context
    private val dialog = Dialog(context)

    private var list = arrayOf(R.drawable.img_avatar_1, R.drawable.img_avatar_2, R.drawable.img_avatar_3)
    private val MIN_SCALE = 0.85f // 뷰가 몇퍼센트로 줄어들 것인지
    private val MIN_ALPHA = 0.5f // 어두워지는 정도를 나타낸 듯 하다.

    fun createDialog() {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        var binding = DialogAvatarBinding.inflate(LayoutInflater.from(context))
        binding.vpAvatar.adapter = AvatarAdapter(list)
        binding.vpAvatar.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpAvatar.setPageTransformer(ZoomOutPageTransformer())

        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout((size.x * 0.9).toInt(), (size.y * 0.5).toInt())
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        binding.btnConfirm.setOnClickListener {
            itemClickListner.onClick(binding.vpAvatar.currentItem)
            dialog.dismiss()
        }

        dialog.show()
    }

    interface ItemClickListener {
        fun onClick(selectedAvatar: Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

    inner class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> alpha = 0f
                    position <= 1 -> {
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        alpha = (MIN_ALPHA + (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> alpha = 0f
                }
            }
        }
    }
}