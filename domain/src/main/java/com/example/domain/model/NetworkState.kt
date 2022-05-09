package com.example.domain.model

sealed class NetworkState<out T> {
    class Success<T>(val data:T): NetworkState<T>()
    class Failure(val exception: Exception): NetworkState<Nothing>()
}
