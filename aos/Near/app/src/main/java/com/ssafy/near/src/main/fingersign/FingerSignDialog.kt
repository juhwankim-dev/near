package com.ssafy.near.src.main.fingersign

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.widget.TextView
import com.ssafy.near.R

class FingerSignDialog(context: Context) {
    private val context = context
    private val dialog = Dialog(context)

    // TODO: 2022-01-20 나중에 dto가 정해지면 string 파라미터를 변경합니다.
    fun createDialog(fingerSignItem: String) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        dialog.setContentView(R.layout.dialog_finger_sign)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout((size.x * 0.9).toInt(), (size.y * 0.7).toInt())
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        // TODO: 2022-01-20 마찬가지로 dto가 정해지면 다이얼로그 뷰들에 값을 넣습니다.

        var tvClose = dialog.findViewById<TextView>(R.id.tv_close)

        tvClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}