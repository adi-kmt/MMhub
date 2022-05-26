package com.example.domain.usecases

import com.example.domain.model.AccessToken
import com.example.domain.model.NetworkState
import com.example.domain.repository.Repo
import javax.inject.Inject

class GetAccessTokenUseCase
    @Inject constructor(private val repo: Repo) {

    suspend fun getUserAccessToken(code: String): NetworkState<AccessToken> = repo.getUserAccessToken(code)
}