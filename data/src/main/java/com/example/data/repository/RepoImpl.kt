package com.example.data.repository

import android.util.Log
import android.util.Log.ASSERT
import com.example.data.api.ApiService
import com.example.data.api.AuthorizedApiService
import com.example.data.model.DAccessToken
import com.example.data.model.DRepoData
import com.example.data.model.mapper.toCreateResponse
import com.example.data.model.mapper.toDCreateRepoData
import com.example.data.model.mapper.toRepoData
import com.example.data.model.toAccessToken
import com.example.domain.model.*
import com.example.domain.repository.Repo
import com.example.mmhub.GithubRequired
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class RepoImpl
    @Inject constructor(
//        @Named("Unauth")
        private val apiService: ApiService,
//        @Named("Auth")
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

//            Log.w("RepoList", drepoResponse.body().toString())
            drepoResponse.body()?.let { dRepoList ->
//                Log.e("DRepoList", dRepoList.toString())
                val repoData = dRepoList.map {dRepoData ->
//                    Log.e("dRepo", "${dRepoData.name} ${dRepoData.stargazers_count} ${dRepoData.description} ${dRepoData.language}")


                    val data = RepoData(
                        dRepoData.name,
                        dRepoData.stargazers_count,
                        dRepoData.description,
                        dRepoData.language,
                    )
//                    Log.e("RepoedData", data.toString())
//                    dRepoData.toRepoData()
                    data
                }
//                Log.e("RepoData", repoData.toString())
                return NetworkState.Success(repoData)
            }?: return NetworkState.Failure(Exception("Empty Repo List received"))
        }catch (e:Exception){
//            Log.e("RepoList Error", e.toString())
            return NetworkState.Failure(e)
        }
    }

    override suspend fun createRepo(
        name: String,
        desc: String,
        private: Boolean,
        template: Boolean
    ): NetworkState<CreateRepoResponse> {
        try {
            val body = CreateRepoData(
                name = name,
                description = desc,
                private = private,
                template = template
            )
            val creatRepoResponse = authorizedApiService.createRepo(body.toDCreateRepoData())
            creatRepoResponse.body()?.let {dCreateRepoResponse ->
                return NetworkState.Success(dCreateRepoResponse.toCreateResponse())
            }?: return NetworkState.Failure(exception = Exception("Access token null"))
        }catch (e:Exception){
            return NetworkState.Failure(e)
        }
    }
}