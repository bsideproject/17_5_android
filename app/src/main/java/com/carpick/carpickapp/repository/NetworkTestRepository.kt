package com.carpick.carpickapp.repository

import com.carpick.carpickapp.api.ApiService
import com.carpick.carpickapp.model.ApiTestResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkTestRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getSearchResult(): Flow<ApiTestResponseModel.Response> {
        return flow {
            val response = apiService.searchUsers("token값", "idstudent")
            emit(response)
        }
    }
}