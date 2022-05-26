package com.example.mmhub.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.NetworkState
import com.example.domain.usecases.CreateRepoUseCase
import com.example.mmhub.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CreateRepoVM
    @Inject constructor(private val createRepoUseCase: CreateRepoUseCase)
    :ViewModel() {

    private val _createRepoStateFlow: MutableStateFlow<UIState<Nothing?>> =
        MutableStateFlow(UIState.Empty)

    val createRepoStateFlow: StateFlow<UIState<Nothing?>> = _createRepoStateFlow

        fun createRepo(name:String, desc:String, private:Boolean, template:Boolean){
            viewModelScope.launch(Dispatchers.IO) {
                when(val response = createRepoUseCase.createRepoUseCae(name, desc, private, template)){
                    is NetworkState.Success ->{
                        _createRepoStateFlow.emit(UIState.Loading)
                        _createRepoStateFlow.emit(UIState.Success(null))
                    }
                    is NetworkState.Failure -> {
                        _createRepoStateFlow.emit(UIState.Loading)
                        _createRepoStateFlow.emit(UIState.Failure(Exception("Create Repo failed")))
                    }

                }
            }
        }

}