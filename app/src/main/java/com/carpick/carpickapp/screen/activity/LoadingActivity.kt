package com.carpick.carpickapp.screen.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.ActivityLoadingBinding
import com.carpick.carpickapp.screen.ComposeTestActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingActivity : BaseActivity<ActivityLoadingBinding>() {
    private val imgList by lazy { listOf(binding.firstImg, binding.secondImg, binding.thirdImg) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Glide.with(this)
            .asGif()
            .load(R.drawable.tire_motion)
            .into(binding.ivLoading)

        lifecycleScope.launch {
            loadGifSequentially(imgList)
        }
    }

    private suspend fun loadGifSequentially(imageViews: List<AppCompatImageView>) {
        for (imageView in imageViews) {

            val requestOptions = RequestOptions().signature(ObjectKey(System.currentTimeMillis()))

            Glide.with(this)
                .asGif()
                .load(R.drawable.loading_motion)
                .apply(requestOptions)
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
                .into(imageView)

            delay(1000)
        }

        val intent = Intent(binding.root.context, ComposeTestActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {}
    override fun getViewBinding(): ActivityLoadingBinding {
        return ActivityLoadingBinding.inflate(layoutInflater)
    }
}