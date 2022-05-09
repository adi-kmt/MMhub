package com.example.data.api

import com.example.data.model.DCreateRepoData
import com.example.data.model.DRepoData
import com.example.domain.model.NetworkState
import javax.inject.Inject

class ApiImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getRepos():NetworkState<List<DRepoData>> = apiService.getRepos()

    suspend fun createRepo(dCreateRepoData: DCreateRepoData) = apiService.createRepo(dCreateRepoData)
}