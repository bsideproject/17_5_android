package com.carpick.carpickapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.repository.WishListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarPickWishListViewModel @Inject constructor(
    private val wishListRepository : WishListRepository
) : ViewModel(){
    fun getTestData(): Flow<TestModel> {
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
}