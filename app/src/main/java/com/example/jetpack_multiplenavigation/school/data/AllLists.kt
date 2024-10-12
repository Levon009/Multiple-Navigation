package com.example.jetpack_multiplenavigation.school.data

import com.example.jetpack_multiplenavigation.school.domain.modul.Student3
import com.example.jetpack_multiplenavigation.school.domain.modul.StudentSubjectCrossRef
import com.example.jetpack_multiplenavigation.school.domain.modul.Subject3

val students3 = listOf(
    Student3(
        "Levon Hakobyan",
        3
    ),
    Student3(
        "Vahan Hakobyan",
        8
    ),
    Student3(
        "vahagn Andreasyan",
        4
    ),
    Student3(
        "Armen Hakobyan",
        5
    ),
    Student3(
        "Elia Hakobyan",
        7
    ),
    Student3(
        "Lionel Messi",
        9
    ),
    Student3(
        "Bef Jezzos",
        6
    ),
    Student3(
        "Lilit Yeghoyan",
        1
    )
)

val subjects3 = listOf(
    Subject3("Dating for programmers"),
    Subject3("Avoiding depression"),
    Subject3("Bug fix meditation"),
    Subject3("Logcat for Newbies"),
    Subject3("How to use Google"),
    Subject3("Kotlin"),
    Subject3("Java"),
    Subject3("C++"),
    Subject3("Swift"),
    Subject3("JavaScript"),
    Subject3("English"),
    Subject3("C#"),
    Subject3("Perl"),
    Subject3("Python"),
    Subject3("Math")
)

val studentSubject3Rel = listOf(
    StudentSubjectCrossRef(
        "Bef Jezzos",
        "Dating for programmers"
    ),
    StudentSubjectCrossRef(
        "Levon Hakobyan",
        "Kotlin"
    ),
    StudentSubjectCrossRef(
        "Elia Hakobyan",
        "Python"
    ),
    StudentSubjectCrossRef(
        "Armen Hakobyan",
        "Math"
    ),
    StudentSubjectCrossRef(
        "Vahan Hakobyan",
        "English"
    ),
    StudentSubjectCrossRef(
        "Vahagn Andreasyan",
        "C++"
    ),
    StudentSubjectCrossRef(
        "Lilit Yeghoyan",
        "Java"
    ),
    StudentSubjectCrossRef(
        "Levon Hakobyan",
        "Java"
    ),
    StudentSubjectCrossRef(
        "Levon Hakobyan",
        "C++"
    ),
    StudentSubjectCrossRef(
        "Armen Hakobyan",
        "English"
    ),
    StudentSubjectCrossRef(
        "Levon Hakobyan",
        "Math"
    )
)