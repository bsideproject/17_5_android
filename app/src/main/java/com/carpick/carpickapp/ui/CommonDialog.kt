package com.carpick.carpickapp.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import com.carpick.carpickapp.databinding.DialogCommonBinding
import com.carpick.carpickapp.util.setOnSingleClickListener

class CommonDialog(context: Context) : AlertDialog(context) {

    private lateinit var binding: DialogCommonBinding
    private var title: String? = null
    private var btnOkText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCommonBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.tvTitle.text = title

        btnOkText?.let { binding.tvSub.text = it }
        initListener()
    }

    private fun initListener() {
        binding.run {

            btnClose.setOnSingleClickListener {
                dismiss()
            }
            btnOk.setOnSingleClickListener {
                dismiss()
            }
        }
    }

    fun setButtonText(text: String) {
        this.btnOkText = text
    }

    fun setTitle(title: String) {
        this.title = title
    }

}