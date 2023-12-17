package com.carpick.carpickapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.carpick.carpickapp.model.Choice
import com.carpick.carpickapp.model.QnAListResponseModelItem
import com.carpick.carpickapp.model.TestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarpickAnswerViewModel @Inject constructor() : ViewModel() {
    private var _answerResult = HashMap<Int,Choice>()
    val answerResult : HashMap<Int,Choice>
        get() = _answerResult

    private var _lastPage = 1
    val lastPage : Int
        get() = _lastPage

    private var answerBudgetResult : Choice?= null

    private var _apiResponse = ArrayList<QnAListResponseModelItem>()
    val apiResponse : ArrayList<QnAListResponseModelItem>
        get() = _apiResponse

    fun setApiResponse(response : ArrayList<QnAListResponseModelItem>) {
        _apiResponse = response
    }
    fun saveAnswerResult(answer : HashMap<Int,Choice>) {
        _answerResult = answer
    }

    fun getBudgetResult() : Choice? {
        return answerBudgetResult
    }
    fun saveBudgetResult(answer : Choice) {
        answerBudgetResult = answer
    }

    fun saveLastPage(page : Int) {
        _lastPage = page
    }
}