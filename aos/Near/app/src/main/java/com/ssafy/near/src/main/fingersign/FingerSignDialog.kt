package com.ssafy.near.src.main.fingersign

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.WindowManager
import com.ssafy.near.databinding.DialogFingerSignBinding
import com.ssafy.near.dto.FingerSignInfo

class FingerSignDialog(context: Context) {
    private val context = context
    private val dialog = Dialog(context)

    fun createDialog(fingerSignInfo: FingerSignInfo) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        var binding = DialogFingerSignBinding.inflate(LayoutInflater.from(context))
        binding.fingerSignInfo = fingerSignInfo

        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout((size.x * 0.9).toInt(), (size.y * 0.7).toInt())
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        binding.tvClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}