package com.example.dog.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dog.model.Repository
import com.example.dog.model.dao.BreedsDao
import com.example.dog.model.dao.ImgBreedDao
import com.example.dog.model.dblocal.Breed
import com.example.dog.model.dblocal.BreedDataBase

class BreddsViewModel (application: Application): AndroidViewModel(application){

    private var repository: Repository

    init {
        val breeds = BreedDataBase.getDatabase(application).getBreedsDao()
        val img = BreedDataBase.getDatabase(application).getImgBreedDao()
        repository = Repository(breeds,img)
        repository.obtainDataInternet()
    }

    fun exposeLiveDataFromDatabase(): LiveData<List<Breed>>{
        return repository.mLiveData
    }
}