package com.example.data.api

import com.example.data.model.DAccessToken
import com.example.data.model.DRepoData
import com.example.domain.model.NetworkState
import retrofit2.Response
import javax.inject.Inject

class AuthorizedApiImpl
    @Inject constructor(private val authorizedApiService: AuthorizedApiService){

    suspend fun getRepoList(): Response<List<DRepoData>> = authorizedApiService.getRepoList()
}