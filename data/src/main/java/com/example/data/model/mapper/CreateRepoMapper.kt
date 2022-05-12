package com.example.data.model.mapper

import com.example.data.model.DCreateRepoData
import com.example.data.model.DCreateRepoResponse
import com.example.domain.model.CreateRepoData
import com.example.domain.model.CreateRepoResponse

fun CreateRepoData.toDCreateRepoData()= DCreateRepoData(
    name = name,
    description = description,
    template = template,
    private = private
)

fun DCreateRepoData.toCreateRepoData() = CreateRepoData(
    name = name,
    description = description,
    template = template,
    private = private
)

fun DCreateRepoResponse.toCreateResponse() = CreateRepoResponse(
    id = id,
    fullName = full_name
)