package com.example.domain.di

import com.example.domain.repository.MainRepository
import com.example.domain.usecases.CreateRepoUsecase
import com.example.domain.usecases.GetReposUsecase
import com.example.domain.usecases.UsecaseList
import dagger.Module
import dagger.Provides
import jdk.jfr.internal.Repository
import javax.inject.Singleton

@Module
@Singleton
object InjectUsecase {

    @Provides
    fun providesUseCases(repository: MainRepository):UsecaseList = UsecaseList(
        getReposUsecase = GetReposUsecase(repository),
        createRepoUsecase = CreateRepoUsecase(repository)
    )
}