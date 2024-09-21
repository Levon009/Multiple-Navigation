package com.example.jetpack_multiplenavigation

import android.app.Application
import com.example.jetpack_multiplenavigation.students.di.testInitializeKoin
import org.koin.android.ext.koin.androidContext

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        testInitializeKoin{
            androidContext(this@TestApplication)
        }
    }
}