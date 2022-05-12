package com.example.data.model

import com.google.gson.annotations.SerializedName

data class DCreateRepoData(

    val name:String,
    val description:String,
    @SerializedName("is_template")
    val template:Boolean,
    val private:Boolean
)