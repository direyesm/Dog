package com.example.dog.model.dblocal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed_table")
data class Breed (@PrimaryKey var breed: String)
