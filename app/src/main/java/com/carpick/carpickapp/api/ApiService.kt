package com.carpick.carpickapp.api

import com.carpick.carpickapp.model.QnAListResponseModel
import com.carpick.carpickapp.model.RecommendCars
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.model.RequestRecommend
import com.carpick.carpickapp.model.SendFeedbackBody
import com.carpick.carpickapp.model.SendFeedbackResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("car-recommendation/questions")
    suspend fun getQnAList(
    ) : ApiResponse<List<QnAListResponseModel>>

    @POST("car-recommendation")
    @Headers("content-type: application/json")
    suspend fun getRecommendCars(
        @Body choices: RequestRecommend
    ) : RecommendCars

    @POST("api/v1/feedback")
    @Headers("content-type: application/json")
    suspend fun sendFeedback(
        @Body feedback: SendFeedbackBody
    ): SendFeedbackResponse

    @GET("car/{ids}")
    suspend fun getCarDetail(
        @Path("ids") ids: String
    ): List<RecommendedCar>

}
