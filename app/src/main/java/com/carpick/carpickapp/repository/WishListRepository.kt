package com.carpick.carpickapp.repository

import com.carpick.carpickapp.db.CarPickDao
import com.carpick.carpickapp.model.TestModel
import kotlinx.coroutines.flow.Flow

class WishListRepository(
    private val dao : CarPickDao
) {
    fun getTestData() : Flow<TestModel> {
        return dao.selectWishList()
    }

    suspend fun insert(testData : TestModel) {
        return dao.insertWishList(testData)
    }

    suspend fun delete(testData: TestModel) {
        return dao.deleteWishList(testData)
    }
}