package com.carpick.carpickapp.repository

import android.util.Log
import com.carpick.carpickapp.api.ApiService
import com.carpick.carpickapp.model.ApiTestResponseModel
import com.carpick.carpickapp.model.QnAListResponseModel
import com.carpick.carpickapp.model.RecommendCars
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.model.RequestRecommend
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QnaListRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getQnaList(exception: (String?) -> Unit): Flow<List<QnAListResponseModel>> {
        return flow {
            apiService.getQnAList()
                .suspendOnSuccess {
                    val response = this.response.body()
                    response?.let { emit(it) }
                }
                .onError {  }
                .onException {
                    exception(this.exception.message)
                }

        }
    }

    fun getRecommendCars(answer : List<String>): Flow<RecommendCars?> {
        return flow {
            val response = apiService.getRecommendCars(RequestRecommend(answer))
            emit(response ?: RecommendCars(emptyList()))
        }
    }
}
