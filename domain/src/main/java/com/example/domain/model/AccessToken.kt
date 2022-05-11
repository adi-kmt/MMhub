package com.example.domain.model

data class AccessToken(
    val access_token: String,
    val scope: String,
    val token_type: String
)