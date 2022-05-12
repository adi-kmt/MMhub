package com.example.data.api

import com.example.data.model.DCreateRepoData
import com.example.data.model.DCreateRepoResponse
import com.example.data.model.DRepoData
import retrofit2.Response
import javax.inject.Inject

class AuthorizedApiImpl
    @Inject constructor(private val authorizedApiService: AuthorizedApiService){

    suspend fun getRepoList(): Response<List<DRepoData>> = authorizedApiService.getRepoList()

    suspend fun createRepo(body:DCreateRepoData):Response<DCreateRepoResponse> = authorizedApiService.createRepo(body)
}