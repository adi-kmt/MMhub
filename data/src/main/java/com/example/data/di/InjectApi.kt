package com.example.data.di

import com.example.data.api.ApiService
import com.example.data.api.AuthorizedApiService
import com.example.data.datastore.PreferencesDatastore
import com.example.mmhub.GithubRequired
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object InjectApi {

    @Provides
    fun providesApi(): ApiService =
        Retrofit.Builder().baseUrl(GithubRequired.TOKENURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    fun providesAuthApi(preferencesDatastore: PreferencesDatastore): AuthorizedApiService {
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val accessToken = runBlocking { preferencesDatastore.readData("access_token") }
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
            chain.proceed(newRequest)
        }).build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(GithubRequired.REPOURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthorizedApiService::class.java)
    }
}