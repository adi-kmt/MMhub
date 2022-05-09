package com.example.domain.repository

import com.example.domain.model.CreateRepoData
import com.example.domain.model.NetworkState
import com.example.domain.model.RepoData

interface MainRepository {

    suspend fun getRepos():NetworkState<List<RepoData>>

    suspend fun createRepo(repoData: CreateRepoData)//:NetworkState<>
}