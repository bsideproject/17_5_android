package com.carpick.carpickapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.carpick.carpickapp.model.TestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarpickAnswerViewModel @Inject constructor() : ViewModel() {
    private var _answerResult = HashMap<Int,TestModel>()
    val answerResult : HashMap<Int,TestModel>
        get() = _answerResult

    private var _lastPage = 1
    val lastPage : Int
        get() = _lastPage

    private var answerBudgetResult : TestModel?= null

    private var _apiResponse = ArrayList<ArrayList<TestModel>>()
    val apiResponse : ArrayList<ArrayList<TestModel>>
        get() = _apiResponse

    fun setApiResponse(response : ArrayList<ArrayList<TestModel>>) {
        _apiResponse = response
    }
    fun saveAnswerResult(answer : HashMap<Int,TestModel>) {
        _answerResult = answer
    }

    fun getBudgetResult() : TestModel? {
        return answerBudgetResult
    }
    fun saveBudgetResult(answer : TestModel) {
        answerBudgetResult = answer
    }

    fun saveLastPage(page : Int) {
        _lastPage = page
    }
}