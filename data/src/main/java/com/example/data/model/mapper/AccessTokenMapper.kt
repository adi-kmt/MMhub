package com.example.data.model

import com.example.domain.model.AccessToken

fun DAccessToken.toAccessToken() = AccessToken(
    access_token = access_token,
    scope = scope,
    token_type = token_type
)