package com.example.domain.usecases

import com.example.domain.model.CreateRepoResponse
import com.example.domain.model.NetworkState
import com.example.domain.repository.Repo
import javax.inject.Inject

class CreateRepoUseCase
    @Inject constructor(private val repo: Repo) {

    suspend fun createRepoUseCae(name:String, desc:String, private:Boolean, template:Boolean):NetworkState<CreateRepoResponse> = repo.createRepo(name, desc, private, template)
}