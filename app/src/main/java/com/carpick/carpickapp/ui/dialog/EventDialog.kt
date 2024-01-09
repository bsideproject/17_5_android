package com.carpick.carpickapp.ui.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.DialogEventBinding
import com.carpick.carpickapp.util.AppPref
import com.carpick.carpickapp.util.setOnSingleClickListener

class EventDialog : DialogFragment() {
    private lateinit var binding: DialogEventBinding
    private var imgUrl = ""
    private var redirectUrl = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogEventBinding.inflate(LayoutInflater.from(context))

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        imgUrl = arguments?.getString("imgUrl", imgUrl) ?: ""
        redirectUrl = arguments?.getString("redirectUrl", redirectUrl) ?: ""

        Glide.with(this)
            .load(imgUrl)
            .into(binding.ivEvent)

        initListener()

        return binding.root
    }
    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    private fun initListener() {

        binding.run {
            ivEvent.setOnSingleClickListener {
                if(redirectUrl != "") {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(redirectUrl))
                    startActivity(intent)
                }

            }

            tvClose.setOnSingleClickListener {
                AppPref.eventPopupCheck = !cbToday.isChecked
                dismiss()
            }
        }
    }

    companion object {
        @JvmStatic
        fun getInstance(imgUrl:String, redirectUrl: String) =
            EventDialog().apply {
                arguments = Bundle().apply {
                    putString("imgUrl", imgUrl)
                    putString("redirectUrl", redirectUrl)
                }
            }
    }

}