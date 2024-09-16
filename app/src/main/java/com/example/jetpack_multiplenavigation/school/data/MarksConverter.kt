package com.example.jetpack_multiplenavigation.school.data

import androidx.room.TypeConverter
import com.example.jetpack_multiplenavigation.school.data.modul.Mark
import com.google.gson.Gson

class MarksConverter {
    @TypeConverter
    fun listToJson(value: List<Mark>?) : String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) : List<Mark> = Gson().fromJson(value, Array<Mark>::class.java).toList()
}