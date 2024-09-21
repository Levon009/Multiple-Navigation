package com.example.jetpack_multiplenavigation.students.di

import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun testInitializeKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        androidLogger()
        config?.invoke(this)
        modules(
            studentsDatabaseModule,
            instrumentedTestModule
        )
    }
}
