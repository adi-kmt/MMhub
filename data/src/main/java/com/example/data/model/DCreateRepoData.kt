package com.example.data.model

data class DCreateRepoData(
    val repoName:String,
    val repoDesc:String,
    val private:Boolean,
    val template:Boolean
)
