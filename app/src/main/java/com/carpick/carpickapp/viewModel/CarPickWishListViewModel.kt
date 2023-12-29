package com.carpick.carpickapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carpick.carpickapp.api.ApiService
import com.carpick.carpickapp.model.QnAListResponseModel
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.repository.WishListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarPickWishListViewModel @Inject constructor(
    private val wishListRepository : WishListRepository
) : ViewModel(){

    fun getTest(): Flow<List<QnAListResponseModel>> {
        return wishListRepository.getQnaList()
            .catch { it.printStackTrace() }

    }
    fun getTestData(): Flow<List<TestModel>> {
        return wishListRepository.getTestData()
    }
    fun insertTestData(testModel : TestModel) {
        viewModelScope.launch(Dispatchers.IO) {
            wishListRepository.insert(testModel)
        }
    }

    fun deleteTestData(testModel : TestModel) {
        viewModelScope.launch(Dispatchers.IO) {
            wishListRepository.delete(testModel)
        }
    }

    fun getWishlistData(): Flow<List<TestModel>> {
        return wishListRepository.getWishlistData()
    }
    fun insertWishlistData(testModel : TestModel) {
        viewModelScope.launch(Dispatchers.IO) {
            wishListRepository.insert(testModel)
        }
    }

    fun deleteWishlistById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            wishListRepository.deleteWishlistById(id)
        }
    }
}