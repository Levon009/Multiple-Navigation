package com.example.draf.students.data

import androidx.compose.runtime.toMutableStateList
import com.example.draf.students.data.model.Student

val allStudents = listOf(
    Student(
        id = 0,
        firstName = "Levon3",
        secondName = "Hakobyan3",
        age = 34
    ),
    Student(
        id = 1,
        firstName = "Elia3",
        secondName = "Hakobyan3",
        age = 29
    ),
    Student(
        id = 2,
        firstName = "Armen3",
        secondName = "Hakobyan3",
        age = 36
    ),
    Student(
        id = 3,
        firstName = "Vahan3",
        secondName = "Hakobyan3",
        age = 33
    ),
    Student(
        id = 4,
        firstName = "Suren3",
        secondName = "Hakobyan3",
        age = 41
    ),
    Student(
        id = 5,
        firstName = "Lionel3",
        secondName = "Messi3",
        age = 40
    ),
    Student(
        id = 6,
        firstName = "Lilit3",
        secondName = "Yeghoyan3",
        age = 40
    ),
).toMutableStateList()