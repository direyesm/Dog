package com.example.dog.model.dbremore

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBreeds {

    companion object{

        private const val BASE_URL = "https://dog.ceo/api"

        fun getRetrofitBreeads(): ApiInterface{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return  retrofit.create(ApiInterface::class.java)
        }
    }
}