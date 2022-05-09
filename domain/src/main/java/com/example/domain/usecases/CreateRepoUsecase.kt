package com.example.domain.usecases

import com.example.domain.model.CreateRepoData
import com.example.domain.repository.MainRepository
import jdk.jfr.internal.Repository
import javax.inject.Inject

class CreateRepoUsecase
    @Inject constructor(private val repository: MainRepository) {

    suspend operator fun invoke(createRepoData: CreateRepoData){
        TODO("Create a github repo from repo data")
    }
}