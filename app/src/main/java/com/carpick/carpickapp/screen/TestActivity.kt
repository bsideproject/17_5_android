package com.carpick.carpickapp.screen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.carpick.carpickapp.databinding.ActivityTestBinding
import com.carpick.carpickapp.viewModel.CarPickWishListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TestActivity : BaseActivity<ActivityTestBinding>() {
    private val carPickWishListViewModel : CarPickWishListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            carPickWishListViewModel.getTestData().collect {
                it?.let {
                    binding.dbTestResult.text = it.toString() ?: ""
                }

            }
        }
    }
    override fun getViewBinding(): ActivityTestBinding {
        return ActivityTestBinding.inflate(layoutInflater)
    }
}