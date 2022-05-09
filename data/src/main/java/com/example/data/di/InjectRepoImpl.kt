package com.example.data.di

import com.example.data.api.ApiService
import com.example.data.repository.RepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
@Singleton
object InjectRepoImpl {
    @Provides
    fun providesRepoImpl(apiService: ApiService) = RepoImpl(apiService = apiService)
}