package com.example.dog.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.dog.model.dao.BreedsDao
import com.example.dog.model.dao.ImgBreedDao
import com.example.dog.model.dblocal.Breed
import com.example.dog.model.dblocal.ImageBreed
import com.example.dog.model.dbremore.RetrofitBreeds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(private val breedsDao: BreedsDao, private val imgBreedDao: ImgBreedDao) {

    private val service = RetrofitBreeds.getRetrofitBreeads()
    val mLiveData = breedsDao.getAllBreedsFromDB()
    val mLiveData2 = imgBreedDao.getAllImgFromDB()

    fun obtainBreedinByID(breed: String): LiveData<Breed>{
        return breedsDao.getBreedByID(breed)
    }

    fun ontainImginByID(imgUrl: String): LiveData<ImageBreed>{
        return imgBreedDao.getImgByID(imgUrl)
    }

    fun getDataFromServerWithCoroutines() = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { service.getDataFromBreed()}
        service.onSuccess {
            when (it.code()){
                in 200..299 -> it.body()?.let { it1 -> breedsDao.insertAllBeers(it1) }
                in 300..399 -> Log.d("ERROR", "ERROR de Parametros ETC")
            }
        }
        service.onFailure {
            Log.e("REPO_ERROR", it.message.toString())
        }
    }
    


}