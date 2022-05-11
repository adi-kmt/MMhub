package com.example.data.di

import com.example.data.api.ApiService
import com.example.data.api.AuthorizedApiService
import com.example.data.repository.RepoImpl
import com.example.domain.repository.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object InjectRepo {

    @Provides
    fun providesRepo(
        @Named("Unauth")
        apiService: ApiService,
        @Named("Auth")
        authorizedApiService: AuthorizedApiService):Repo = RepoImpl(apiService = apiService, authorizedApiService = authorizedApiService)
}