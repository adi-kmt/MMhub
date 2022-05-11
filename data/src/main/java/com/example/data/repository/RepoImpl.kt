package com.example.data.repository

import android.util.Log
import com.example.data.api.ApiService
import com.example.data.api.AuthorizedApiService
import com.example.data.model.DAccessToken
import com.example.data.model.DRepoData
import com.example.data.model.mapper.toRepoData
import com.example.data.model.toAccessToken
import com.example.domain.model.AccessToken
import com.example.domain.model.NetworkState
import com.example.domain.model.RepoData
import com.example.domain.repository.Repo
import com.example.mmhub.GithubRequired
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class RepoImpl
    @Inject constructor(
        @Named("Unauth")
        private val apiService: ApiService,
        @Named("Auth")
        private val authorizedApiService: AuthorizedApiService)
    :Repo {
    override suspend fun getUserAccessToken(
        code:String,
    ): NetworkState<AccessToken> {
        try {
            val item = apiService.getAccessToken(clientID = GithubRequired.CLIENT_ID,
                clientSecret = GithubRequired.CLIENT_SECRET,
                code = code,
                url = GithubRequired.REDIRECT_URI,
            grant_type = "authorization_code")

                item.body()?.let { accesToken ->
                    return NetworkState.Success(accesToken.toAccessToken())
                }?: return NetworkState.Failure(exception = Exception("Access token null"))
        } catch (e:Exception){
            return NetworkState.Failure(e)
        }
    }

    override suspend fun getRepoList(): NetworkState<List<RepoData>> {
        try {
            val drepoResponse = authorizedApiService.getRepoList()
            drepoResponse.body()?.let { dRepoList ->
                val repoData  = dRepoList.map {dRepoData ->
                    dRepoData.toRepoData()
                }
                return NetworkState.Success(repoData)
            }?: return NetworkState.Failure(Exception("Empty Repo List received"))
        }catch (e:Exception){
            return NetworkState.Failure(e)
        }
    }
}