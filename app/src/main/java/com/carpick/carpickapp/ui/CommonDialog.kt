package com.carpick.carpickapp.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.carpick.carpickapp.databinding.DialogCommonBinding
import com.carpick.carpickapp.util.setOnSingleClickListener

class CommonDialog() : DialogFragment() {

    private lateinit var binding: DialogCommonBinding
    private var title: String? = null
    private var subTitle : String?= null
    private var btnOkText: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogCommonBinding.inflate(LayoutInflater.from(context))

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        initView()
        initListener()

        return binding.root
    }
    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun initView() {
        binding.run {
            tvTitle.text = arguments?.getString("title", "")
            tvSub.text = arguments?.getString("subTitle", "")
            btnOk.text = arguments?.getString("buttonText", "")
        }
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

    companion object {
       @JvmStatic
       fun getInstance(title : String, subTitle : String, buttonText : String) =
           CommonDialog().apply {
               arguments = Bundle().apply {
                   putString("title", title)
                   putString("subTitle", subTitle)
                   putString("buttonText", buttonText)
               }
           }
    }

}