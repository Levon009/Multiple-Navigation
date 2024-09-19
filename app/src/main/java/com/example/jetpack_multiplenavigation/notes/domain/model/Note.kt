package com.example.jetpack_multiplenavigation.notes.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jetpack_multiplenavigation.ui.theme.BabyBlue
import com.example.jetpack_multiplenavigation.ui.theme.LightGreen
import com.example.jetpack_multiplenavigation.ui.theme.RedOrange
import com.example.jetpack_multiplenavigation.ui.theme.RedPink
import com.example.jetpack_multiplenavigation.ui.theme.Violet

@Entity(tableName = "notes_table")
data class Note(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name ="content")
    val content: String,
    @ColumnInfo(name ="time")
    val timestamp: Long,
    @ColumnInfo(name ="color")
    val color: Int,
    @PrimaryKey
    val id: Int? = null
) {
    companion object {
        val noteColors = listOf(
            RedOrange,
            LightGreen,
            Violet,
            BabyBlue,
            RedPink
        )
    }
}
