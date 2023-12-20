package com.carpick.carpickapp.repository

import android.util.Log
import com.carpick.carpickapp.api.ApiService
import com.carpick.carpickapp.model.ApiTestResponseModel
import com.carpick.carpickapp.model.QnAListResponseModel
import com.carpick.carpickapp.model.RecommendCars
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.model.RequestRecommend
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QnaListRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getQnaList(): Flow<List<QnAListResponseModel>> {
        return flow {
            val response = apiService.getQnAList()
            emit(response)
        }
    }

    fun getRecommendCars(answer : List<String>): Flow<RecommendCars> {
        return flow {
            val response = apiService.getRecommendCars(RequestRecommend(answer))
            emit(response)
        }
    }
}
