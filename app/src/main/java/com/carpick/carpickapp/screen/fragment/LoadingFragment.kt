package com.carpick.carpickapp.screen.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentLoadingBinding
import com.carpick.carpickapp.screen.ComposeTestActivity
import com.carpick.carpickapp.screen.TestActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingFragment : BaseFragment<FragmentLoadingBinding>() {
    private val imgList by lazy { listOf(binding.firstImg, binding.secondImg, binding.thirdImg) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {} }
        )

        Glide.with(this@LoadingFragment)
            .asGif()
            .load(R.drawable.tire_motion)
            .into(binding.ivLoading)

        viewLifecycleOwner.lifecycleScope.launch {
            loadGifSequentially(imgList)
        }
    }
    private suspend fun loadGifSequentially(imageViews: List<AppCompatImageView>) {
        for (imageView in imageViews) {

            val requestOptions = RequestOptions().signature(ObjectKey(System.currentTimeMillis()))

            Glide.with(this@LoadingFragment)
                .asGif()
                .load(R.drawable.loading_motion)
                .apply(requestOptions)
                .apply(RequestOptions().disallowHardwareConfig()) // 이 부분이 핵심입니다
                .listener(object : RequestListener<GifDrawable> { // DataSource를 GifDrawable로 변경
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
                .into(imageView)

            // 1초 대기
            delay(1000)
        }

        val intent = Intent(binding.root.context, ComposeTestActivity::class.java)
        startActivity(intent)
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoadingBinding {
        return FragmentLoadingBinding.inflate(layoutInflater)
    }
}