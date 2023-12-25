package com.carpick.carpickapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.repository.TestResultRepository
import com.carpick.carpickapp.screen.TestResult.ResultDetailOptionRowData
import com.carpick.carpickapp.screen.TestResult.RowDataTypes
import com.carpick.carpickapp.screen.TestResult.fuelTypeName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class CarPickTestResultViewModel @Inject constructor(
    private val testResultRepository: TestResultRepository
) : ViewModel() {

    fun getWishlistData(): Flow<List<TestModel>> {
        return testResultRepository.getWishlistData()
    }

    fun addWishList(selectedId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            testResultRepository.insertWishList(TestModel(selectedId))
        }
    }

    fun deleteWishList(selectedId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            testResultRepository.deleteWishlistById(selectedId)
        }
    }


}