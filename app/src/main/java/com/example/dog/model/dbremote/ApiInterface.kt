package com.example.dog.model.dbremote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET ("breeds/list")
     fun getDataFromBreed() : Call<BreedsList>

    @GET("breed/{breed}/images")
    suspend fun getDataFromImg(@Path("breed")  mBreed: String) : Call<ImagenBreedsList>
}