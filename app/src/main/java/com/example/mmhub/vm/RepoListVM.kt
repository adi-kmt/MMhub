package com.example.mmhub.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.NetworkState
import com.example.domain.model.RepoData
import com.example.domain.usecases.GetRepoListUseCase
import com.example.mmhub.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoListVM
    @Inject constructor(private val getRepoListUseCase: GetRepoListUseCase)
    :ViewModel() {

    private val _repoList: MutableStateFlow<UIState<List<RepoData>>> =
        MutableStateFlow(UIState.Empty)

    val repoList: StateFlow<UIState<List<RepoData>>> = _repoList

        fun repoList(){
            viewModelScope.launch(Dispatchers.IO){
        val items = getRepoListUseCase.getRepoList()
        Log.e("Repos recieved", items.toString())
    }
//                when(val items = getRepoListUseCase.getRepoList()){
//                    is NetworkState.Success ->{
//                        _repoList.emit(UIState.Loading)
//                        _repoList.emit(UIState.Success(items.data))
//                    }
//                    is NetworkState.Failure -> {
//                        _repoList.emit(UIState.Loading)
//                        _repoList.emit(UIState.Failure(items.exception))
//                    }
//                }
        }
}