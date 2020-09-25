package com.example.dog.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dog.model.dblocal.ImageBreed

@Dao
interface ImgBreedDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertAllImgBreed(mImgList: List<ImageBreed>)

    @Query("SELECT * FROM images_table")
    fun getAllImgFromDB(): LiveData<List<ImageBreed>>

    @Query("SELECT * FROM images_table WHERE imgUrl =:imgUrl")
    fun getImgByID(imgUrl: String): LiveData<ImageBreed>
}