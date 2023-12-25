package com.carpick.carpickapp.api

import com.carpick.carpickapp.model.QnAListResponseModel
import com.carpick.carpickapp.model.RecommendCars
import com.carpick.carpickapp.model.RequestRecommend
import com.carpick.carpickapp.model.SendFeedbackBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @GET("car-recommendation/questions")
    suspend fun getQnAList(
    ) : List<QnAListResponseModel>

    @POST("car-recommendation")
    @Headers("content-type: application/json")
    suspend fun getRecommendCars(
        @Body choices: RequestRecommend
    ) : RecommendCars

    @POST("api/v1/feedback")
    @Headers("content-type: application/json")
    suspend fun sendFeedback(
        @Body feedback: SendFeedbackBody
    )

}
