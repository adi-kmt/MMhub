package com.example.data.api

import com.example.data.model.DAccessToken
import com.example.domain.model.NetworkState
import retrofit2.Response
import javax.inject.Inject

class ApiImpl
    @Inject constructor(private val apiService: ApiService){

    suspend fun postData(clientID:String,
                         clientSecret:String,
                         code:String,
                         url:String,
                         grant_type:String): Response<DAccessToken> =
        apiService.getAccessToken(clientID = clientID, clientSecret = clientSecret, code = code, url = url, grant_type = grant_type)
}