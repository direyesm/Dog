package com.example.dog.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dog.model.dblocal.Breed
import com.example.dog.model.dbremore.BreedsList

@Dao
interface BreedsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBeers(mBreedsList: BreedsList)

    @Query("SELECT * FROM breed_table")
    fun getAllBreedsFromDB(): LiveData<List<Breed>>

    @Query("SELECT * FROM breed_table WHERE breed =:breed")
    fun getBreedByID(breed: String): LiveData<Breed>
}