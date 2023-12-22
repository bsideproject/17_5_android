package com.carpick.carpickapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.carpick.carpickapp.model.Choice
import com.carpick.carpickapp.model.QnAListResponseModel
import com.carpick.carpickapp.model.RecommendCars
import com.carpick.carpickapp.repository.QnaListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class CarpickAnswerViewModel @Inject constructor(
    private val qnaListRepository: QnaListRepository
) : ViewModel() {
    private var _answerResult = HashMap<Int,Choice>()
    val answerResult : HashMap<Int,Choice>
        get() = _answerResult

    private var _lastPage = 1
    val lastPage : Int
        get() = _lastPage

    private var answerBudgetResult : Choice?= null
    private var answerUserInfoResult : Choice?= null

    private var _apiResponse = ArrayList<QnAListResponseModel>()
    val apiResponse : ArrayList<QnAListResponseModel>
        get() = _apiResponse

    fun setApiResponse(response: List<QnAListResponseModel>) {
        _apiResponse = response as ArrayList<QnAListResponseModel>
    }
    fun saveAnswerResult(answer: HashMap<Int, Choice>?) {
        answer?.let {
            _answerResult = it
        }
    }

    fun getBudgetResult() : Choice? {
        return answerBudgetResult
    }
    fun saveBudgetResult(answer : Choice?) {
        answerBudgetResult = answer
    }

    fun getUserInfo() : Choice?{
        return answerUserInfoResult
    }
    fun saveUserInfo(answer : Choice?) {
        answerUserInfoResult = answer
    }

    fun saveLastPage(page : Int) {
        _lastPage = page
    }

    fun getQnaList(): Flow<List<QnAListResponseModel>> {
        return qnaListRepository.getQnaList()
            .catch { it.printStackTrace() }
    }

    fun getRecommendCars(answer : List<String>) : Flow<RecommendCars>{
        return qnaListRepository.getRecommendCars(answer)
            .catch { it.printStackTrace() }
    }
}