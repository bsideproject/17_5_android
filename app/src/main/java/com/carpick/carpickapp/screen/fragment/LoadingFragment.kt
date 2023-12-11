package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.bumptech.glide.Glide
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentLoadingBinding

class LoadingFragment : BaseFragment<FragmentLoadingBinding>() {
    private val progressBarList by lazy {  listOf(binding.firstProgress, binding.secondProgress, binding.thirdProgress) }
    private val imgList by lazy { listOf(binding.firstImg, binding.secondImg, binding.thirdImg) }
    private var currentIndex = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this@LoadingFragment)
            .asGif()
            .load(R.drawable.tire_motion)
            .into(binding.ivLoading)

//        // 애니메이션 리스너 설정
//        val animationListener = object : Animation.AnimationListener {
//            override fun onAnimationStart(animation: Animation?) {
//                // 애니메이션이 시작될 때 실행되는 코드
//            }
//
//            override fun onAnimationEnd(animation: Animation?) {
//                // 애니메이션이 종료될 때 실행되는 코드
//                // 이 부분에서 Drawable을 교체하거나 다른 동작을 수행할 수 있습니다.
//
//                // 예시: Drawable 교체
//                binding.firstImg.visibility=  View.VISIBLE
//                binding.firstProgress.visibility = View.GONE
//            }
//
//            override fun onAnimationRepeat(animation: Animation?) {
//                // 애니메이션이 반복될 때 실행되는 코드
//            }
//        }
//
//        // 애니메이션 설정
//        val rotateAnimation = AnimationUtils.loadAnimation(binding.root.context, R.anim.rotate_animation)
//        rotateAnimation.setAnimationListener(animationListener)
//
//        // 프로그레스바에 애니메이션 적용
//        binding.firstProgress.startAnimation(rotateAnimation)

//        startNextProgressBarAnimation()
    }
//    private fun startNextProgressBarAnimation() {
//        if (currentIndex < progressBarList.size) {
//            val currentProgressBar = progressBarList[currentIndex]
//            val currentImage = imgList[currentIndex]
//
//            // 애니메이션 리스너 설정
//            val animationListener = object : Animation.AnimationListener {
//                override fun onAnimationStart(animation: Animation?) {
//                    // 애니메이션이 시작될 때 실행되는 코드
//                }
//
//                override fun onAnimationEnd(animation: Animation?) {
//                    // 애니메이션이 종료될 때 실행되는 코드
//                    // 이 부분에서 Drawable을 교체하거나 다른 동작을 수행할 수 있습니다.
//
//                    // 프로그레스바 숨기고 이미지 나타내기
//                    currentProgressBar.visibility = View.INVISIBLE
//                    currentImage.visibility = View.VISIBLE
//
//
//                    // 1초 후에 다음 프로그레스바 애니메이션 시작
//                    Handler(Looper.getMainLooper()).postDelayed({
//                        // 이미지 숨기기
//                        currentImage.visibility = View.INVISIBLE
//                        currentIndex++
//
//                        // 다음 프로그레스바 애니메이션 시작
//                        startNextProgressBarAnimation()
//                    }, 1000)
//                }
//
//                override fun onAnimationRepeat(animation: Animation?) {
//                    // 애니메이션이 반복될 때 실행되는 코드
//                }
//            }
//
//            // 애니메이션 설정
//            val rotateAnimation = AnimationUtils.loadAnimation(binding.root.context, R.anim.rotate_animation)
//            rotateAnimation.setAnimationListener(animationListener)
//
//            // 프로그레스바에 애니메이션 적용
//            currentProgressBar.startAnimation(rotateAnimation)
//        }
//    }
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoadingBinding {
        return FragmentLoadingBinding.inflate(layoutInflater)
    }
}