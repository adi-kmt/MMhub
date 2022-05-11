package com.example.domain.di

import com.example.domain.repository.Repo
import com.example.domain.usecases.GetAccessTokenUseCase
import com.example.domain.usecases.GetRepoListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InjectUserTokenUsecase {

    @Provides
    fun providesUserTokenUseCase(repo: Repo):GetAccessTokenUseCase = GetAccessTokenUseCase(repo)

    @Provides
    fun provideRepoListUseCase(repo: Repo):GetRepoListUseCase = GetRepoListUseCase(repo)
}