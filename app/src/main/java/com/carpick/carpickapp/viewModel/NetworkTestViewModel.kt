package com.carpick.carpickapp.viewModel

import androidx.lifecycle.ViewModel
import com.carpick.carpickapp.repository.NetworkTestRepository
import com.carpick.carpickapp.repository.WishListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class NetworkTestViewModel @Inject constructor(
    private val networkTestRepository: NetworkTestRepository
) : ViewModel() {

    fun getSearchResult() =
        networkTestRepository.getSearchResult()
            .catch { it.printStackTrace() }

}