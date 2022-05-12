package com.example.data.model.mapper

import com.example.data.model.DRepoData
import com.example.domain.model.RepoData

fun DRepoData.toRepoData() = RepoData(
    name = name,
    stars = stargazers_count,
    language = language,
    description = description
)