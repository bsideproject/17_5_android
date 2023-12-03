package com.carpick.carpickapp.api

import com.carpick.carpickapp.model.ApiTestResponseModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun searchUsers(
        @Header("Authorization") accessToken: String,
        @Query("q") query : String,
        @Query("page") page : Int = 1,
        @Query("per_page") perPage : Int = 100,
        @Query("order") order : String = "asc"
    ) : ApiTestResponseModel.Response
}