package com.carpick.carpickapp.repository

import com.carpick.carpickapp.api.ApiService
import com.carpick.carpickapp.model.GetNoticeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoticeRepository @Inject constructor(
    private val apiService: ApiService
){
    fun getNotice() : Flow<GetNoticeModel> {
        return flow {
            val response = apiService.getNotice()
            emit(response)
        }
    }
}