package com.carpick.carpickapp.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.carpick.carpickapp.databinding.DialogEventBinding
import com.carpick.carpickapp.util.AppPref
import com.carpick.carpickapp.util.setOnSingleClickListener

class EventDialog : DialogFragment() {
    private lateinit var binding: DialogEventBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogEventBinding.inflate(LayoutInflater.from(context))

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        initListener()

        return binding.root
    }
    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    private fun initListener() {

        binding.run {

            btnClose.setOnSingleClickListener {
                dismiss()
            }
            btnTodayNotShow.setOnSingleClickListener {
                AppPref.eventPopupCheck = false
                dismiss()
            }
            btnOk.setOnSingleClickListener {
                dismiss()
            }
        }
    }

    companion object {
        @JvmStatic
        fun getInstance() = EventDialog()
    }

}