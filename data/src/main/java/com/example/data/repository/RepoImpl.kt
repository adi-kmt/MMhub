package com.example.data.repository

import com.example.data.api.ApiService
import com.example.domain.model.CreateRepoData
import com.example.domain.model.NetworkState
import com.example.domain.model.RepoData
import com.example.domain.repository.MainRepository
import javax.inject.Inject

class RepoImpl
   @Inject constructor(apiService: ApiService)
    :MainRepository {
    override suspend fun getRepos(): NetworkState<List<RepoData>> {
        TODO("Not yet implemented")
    }

    override suspend fun createRepo(repoData: CreateRepoData) {
        TODO("Not yet implemented")
    }
}