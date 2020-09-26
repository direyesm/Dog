package com.example.dog.model.dblocal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dog.model.dao.BreedsDao
import com.example.dog.model.dao.ImgBreedDao
import com.example.dog.model.dbremote.BreedsList
import com.example.dog.model.dbremote.ImagenBreedsList

private const val DATA_BASE_NAME = "bredds_db"

@Database(entities = [Breed::class, ImageBreed::class], version = 1)
abstract class BreedDataBase : RoomDatabase(){

    abstract fun getBreedsDao(): BreedsDao
    abstract fun getImgBreedDao(): ImgBreedDao

    companion object{
        @Volatile
        private  var INSTANCE: BreedDataBase? = null

        fun getDatabase(context: Context): BreedDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context,
                BreedDataBase::class.java,
                DATA_BASE_NAME)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}