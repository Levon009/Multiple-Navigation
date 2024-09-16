package com.example.jetpack_multiplenavigation.contactsRoom1.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class DogListConverters {
    @TypeConverter
    fun listToJson(value: List<Dog>?) : String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) : List<Dog> = Gson().fromJson(value, Array<Dog>::class.java).toList()
}