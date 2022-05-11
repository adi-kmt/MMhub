package com.example.domain.repository

import com.example.domain.model.AccessToken
import com.example.domain.model.NetworkState
import com.example.domain.model.RepoData

interface Repo {
    suspend fun getUserAccessToken(code:String) :NetworkState<AccessToken>

    suspend fun getRepoList():NetworkState<List<RepoData>>
}