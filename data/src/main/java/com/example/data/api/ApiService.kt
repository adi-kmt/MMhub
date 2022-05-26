package com.example.data.api

import com.example.data.model.DAccessToken
import com.example.domain.model.NetworkState
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Headers("Accept: application/vnd.github.v3+json")
    @POST("login/oauth/access_token")
    suspend fun getAccessToken(
        @Query("grant_type") grant_type:String? = "authorization_code",
        @Query("code") code:String,
        @Query("redirect_uri") url:String,
        @Query("client_id") clientID:String,
        @Query("client_secret") clientSecret:String
    ): Response<DAccessToken>
}