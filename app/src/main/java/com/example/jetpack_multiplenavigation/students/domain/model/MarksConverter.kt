package com.example.jetpack_multiplenavigation.students.domain.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class MarksConverter {
    @TypeConverter
    fun listToJson(value: List<StudentMark>?) : String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) : List<StudentMark> = Gson().fromJson(value, Array<StudentMark>::class.java).toList()
}