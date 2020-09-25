package com.example.dog.model.dbremore

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ApiInterface {

    @GET ("breeds/list")
    fun getDataFromBreed() : Response<BreedsList>

    @GET("breed/{breed}/images")
    fun getDataFromImg(@Path("breed")  mBreed: String) : Call<ImagenBreedsList>
}