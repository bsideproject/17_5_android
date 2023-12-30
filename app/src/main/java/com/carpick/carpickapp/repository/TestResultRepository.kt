package com.carpick.carpickapp.repository

import com.carpick.carpickapp.api.ApiService
import com.carpick.carpickapp.db.CarPickDao
import com.carpick.carpickapp.model.SendFeedbackBody
import com.carpick.carpickapp.model.SendFeedbackResponse
import com.carpick.carpickapp.model.TestModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestResultRepository @Inject constructor(
    private val dao : CarPickDao,
    private val apiService: ApiService
) {
    fun getWishlistData() : Flow<List<TestModel>> {
        return dao.selectWishList()
    }

    suspend fun deleteWishlistById(id : Int) {
        return dao.deleteWishListById(id)
    }

    suspend fun insertWishList(testData : TestModel) {
        return dao.insertWishList(testData)
    }

    fun sendFeedback(feedbackBody: SendFeedbackBody): Flow<SendFeedbackResponse> {
        return flow {
            val response = apiService.sendFeedback(feedbackBody)
            emit(response)
        }
    }
}