package com.carpick.carpickapp.viewModel

import androidx.lifecycle.ViewModel
import com.carpick.carpickapp.model.TestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarpickAnswerViewModel @Inject constructor() : ViewModel() {
    private var _answerResult = ArrayList<TestModel>()
    val answerResult : ArrayList<TestModel>
        get() = _answerResult

    private var answerBudgetResult : TestModel?= null

    fun saveAnswerResult(answer : ArrayList<TestModel>) {
        _answerResult = answer
    }

    fun getBudgetResult() : TestModel? {
        return answerBudgetResult
    }
    fun saveBudgetResult(answer : TestModel) {
        answerBudgetResult = answer
    }
}