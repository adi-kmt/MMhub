package com.example.data.di

import com.example.data.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@Singleton
object InjectApi {

    @Provides
    fun providesURL() = ""


    @Provides
    fun providesApi(url:String):ApiService = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}