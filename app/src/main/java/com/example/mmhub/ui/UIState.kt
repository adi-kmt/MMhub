package com.example.mmhub.ui

sealed class UIState<out T> {
    class Success<T>(val data:T): UIState<T>()
    class Failure(val exception: Exception): UIState<Nothing>()
    object Loading : UIState<Nothing>()
    object Empty: UIState<Nothing>()
}
