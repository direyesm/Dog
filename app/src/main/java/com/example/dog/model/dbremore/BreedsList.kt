package com.example.dog.model.dbremore


import com.google.gson.annotations.SerializedName

data class BreedsList(
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: String
)