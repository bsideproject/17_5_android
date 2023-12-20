package com.carpick.carpickapp.api

import com.carpick.carpickapp.model.ApiTestResponseModel
import com.carpick.carpickapp.model.QnAListResponseModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("car-recommendation/questions")
    suspend fun getQnAList(
    ) : List<QnAListResponseModel>
}