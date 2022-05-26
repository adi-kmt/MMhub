package com.example.data.api

import com.example.data.model.DCreateRepoData
import com.example.data.model.DCreateRepoResponse
import com.example.data.model.DRepoData
import retrofit2.Response
import retrofit2.http.*

interface AuthorizedApiService {

    @GET("user/repos")
    suspend fun getRepoList():Response<List<DRepoData>>

    @POST("user/repos")
    suspend fun createRepo(@Body body:DCreateRepoData):Response<DCreateRepoResponse>
}