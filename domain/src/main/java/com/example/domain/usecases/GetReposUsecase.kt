package com.example.domain.usecases

import com.example.domain.repository.MainRepository
import jdk.jfr.internal.Repository
import javax.inject.Inject

class GetReposUsecase
    @Inject constructor(private val repository: MainRepository) {


    suspend operator fun invoke() = repository.getRepos()
}