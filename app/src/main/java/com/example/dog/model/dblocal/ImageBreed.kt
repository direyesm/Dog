package com.example.dog.model.dblocal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images_table")
data class ImageBreed (@PrimaryKey val imgUrl: String)