package com.example.dog.model

import android.util.Log
import com.example.dog.model.dao.BreedsDao
import com.example.dog.model.dao.ImgBreedDao
import com.example.dog.model.dblocal.Breed
import com.example.dog.model.dbremote.BreedsList
import com.example.dog.model.dbremote.RetrofitBreeds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val breedsDao: BreedsDao, private val imgBreedDao: ImgBreedDao) {

    private val service = RetrofitBreeds.getRetrofitBreeads()
    val mLiveData = breedsDao.getAllBreedsFromDB()
    val mLiveData2 = imgBreedDao.getAllImgFromDB()

    fun obtainDataInternet(){
        service.getDataFromBreed().enqueue(object : Callback<BreedsList>{
            override fun onResponse(call: Call<BreedsList>, response: Response<BreedsList>) {
                Log.d("REPO",response.body().toString() )
                when(response.code()){
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let { breedsDao.insertAllBeers(converter(it.message))}}
                        in 300..399 -> Log.d("ERROR 300", response.errorBody().toString() )
                    }
                }
            override fun onFailure(call: Call<BreedsList>, t: Throwable) {
                Log.e("Repository", t.message.toString())
            }
        })
    }

    fun converter(listDog: List<String>) : List<Breed>{
        val dog = mutableListOf<Breed>()
        listDog.map {
            dog.add(Breed(it))
        }
        return dog
    }

}