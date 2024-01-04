package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentNotResultBinding
import com.carpick.carpickapp.util.setOnSingleClickListener


class NoResultFragment : BaseFragment<FragmentNotResultBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }

    private fun initView() {
        binding?.ivNoResult?.let {
            Glide.with(this)
                .asGif()
                .load(R.drawable.noresult_motion)
                .apply(RequestOptions().disallowHardwareConfig())
                .listener(object : RequestListener<GifDrawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<GifDrawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: GifDrawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<GifDrawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        resource?.setLoopCount(1)
                        return false
                    }
                })
                .into(it)
        }
    }

    private fun initListener() {
        binding?.tvRestartTest?.setOnSingleClickListener {
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager

            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

            fragmentManager.beginTransaction()
                .replace(R.id.nav_host, CarPickStartFragment())
                .commit()
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotResultBinding {
        return FragmentNotResultBinding.inflate(layoutInflater)
    }
}