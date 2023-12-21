package com.carpick.carpickapp.screen.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.ActivityLoadingBinding
import com.carpick.carpickapp.model.RecommendCars
import com.carpick.carpickapp.screen.ComposeTestActivity
import com.carpick.carpickapp.screen.DetailSpecActivity
import com.carpick.carpickapp.screen.TestResultActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.Serializable

class LoadingActivity : BaseActivity<ActivityLoadingBinding>() {
    private val imgList by lazy { listOf(binding.firstImg, binding.secondImg, binding.thirdImg) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val response = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("response", RecommendCars::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("response") as RecommendCars
        }

        Glide.with(this)
            .asGif()
            .load(R.drawable.tire_motion)
            .into(binding.ivLoading)

        lifecycleScope.launch {
            response?.let { loadGifSequentially(imgList, it) }
        }
    }

    private suspend fun loadGifSequentially(imageViews: List<AppCompatImageView>, response: RecommendCars) {
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

        Log.e("ljy", "response $response")
        val intent = Intent(binding.root.context, TestResultActivity::class.java)
        intent.putExtra("response", response)
        startActivity(intent)
    }

    override fun onBackPressed() {}
    override fun getViewBinding(): ActivityLoadingBinding {
        return ActivityLoadingBinding.inflate(layoutInflater)
    }
}