package com.carpick.carpickapp.repository

import com.carpick.carpickapp.api.ApiService
import com.carpick.carpickapp.db.CarPickDao
import com.carpick.carpickapp.model.QnAListResponseModel
import com.carpick.carpickapp.model.TestModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WishListRepository(
    private val apiService: ApiService,
    private val dao : CarPickDao
) {
    fun getQnaList(): Flow<List<QnAListResponseModel>> {
        return flow {
            val response = apiService.getQnAList()
            emit(response)
        }
    }
    fun getTestData() : Flow<List<TestModel>> {
        return dao.selectWishList()
    }

    suspend fun insert(testData : TestModel) {
        return dao.insertWishList(testData)
    }

    suspend fun delete(testData: TestModel) {
        return dao.deleteWishList(testData)
    }

    fun getWishlistData() : Flow<List<TestModel>> {
        return dao.selectWishList()
    }

    suspend fun deleteWishlistById(id : Int) {
        return dao.deleteWishListById(id)
    }
}