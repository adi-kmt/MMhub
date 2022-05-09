package com.example.data.api

import com.example.data.model.DCreateRepoData
import com.example.data.model.DRepoData
import com.example.domain.model.NetworkState
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("")
    suspend fun getRepos():NetworkState<List<DRepoData>>

    @POST()
    suspend fun createRepo(dCreateRepoData: DCreateRepoData)
}