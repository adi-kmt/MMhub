package com.example.domain.model


data class CreateRepoData(

    val name:String,
    val description:String,
    val template:Boolean,
    val private:Boolean
)