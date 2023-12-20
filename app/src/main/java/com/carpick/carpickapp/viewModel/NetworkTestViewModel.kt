package com.carpick.carpickapp.viewModel

import androidx.lifecycle.ViewModel
import com.carpick.carpickapp.repository.QnaListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class NetworkTestViewModel @Inject constructor(
    private val networkTestRepository: QnaListRepository
) : ViewModel() {

    fun getSearchResult() =
        networkTestRepository.getQnaList()
            .catch { it.printStackTrace() }

}