package com.carpick.carpickapp.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.carpick.carpickapp.databinding.ActivityTestBinding
import com.carpick.carpickapp.screen.activity.BaseActivity
import com.carpick.carpickapp.viewModel.CarPickWishListViewModel
import com.carpick.carpickapp.viewModel.NetworkTestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TestActivity : BaseActivity<ActivityTestBinding>() {
    private val carPickWishListViewModel: CarPickWishListViewModel by viewModels()
    private val networkTestViewModel : NetworkTestViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            carPickWishListViewModel.getTest().collect {
                Log.e("ljy", "test response $it")
            }
            networkTestViewModel.getSearchResult().collect {
                Log.e("ljy", "$it")
            }
            carPickWishListViewModel.getTestData().collect {
                it?.let {
                    binding.dbTestResult.text = it.toString()
                }
            }
        }
    }

    override fun getViewBinding(): ActivityTestBinding {
        return ActivityTestBinding.inflate(layoutInflater)
    }
}