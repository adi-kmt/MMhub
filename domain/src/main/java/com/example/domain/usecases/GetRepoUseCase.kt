package com.example.domain.usecases

import com.example.domain.model.NetworkState
import com.example.domain.model.RepoData
import com.example.domain.repository.Repo
import javax.inject.Inject

class GetRepoListUseCase
    @Inject constructor(private val repo: Repo){
    suspend fun getRepoList():NetworkState<List<RepoData>> = repo.getRepoList()
}