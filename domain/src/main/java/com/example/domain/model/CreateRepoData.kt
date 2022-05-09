package com.example.domain.model

data class CreateRepoData(
    val repoName:String,
    val repoDesc:String,
    val private:Boolean,
    val template:Boolean
)
