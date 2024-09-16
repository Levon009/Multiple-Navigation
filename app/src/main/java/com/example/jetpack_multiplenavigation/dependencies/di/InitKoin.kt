package com.example.jetpack_multiplenavigation.dependencies.di

import com.example.jetpack_multiplenavigation.bindedServiceTimer.di.timerServiceModule
import com.example.jetpack_multiplenavigation.chat.di.chatFcmModule
import com.example.jetpack_multiplenavigation.contactsRoom1.di.contactsDatabaseModule
import com.example.jetpack_multiplenavigation.downloadManager.di.downloadManageModule
import com.example.jetpack_multiplenavigation.products.di.networkModule
import com.example.jetpack_multiplenavigation.products.di.productsModule
import com.example.jetpack_multiplenavigation.school.di.schoolDBModule
import com.example.jetpack_multiplenavigation.snackBarSB.di.snackBarModule
import com.example.jetpack_multiplenavigation.students.di.studentsDatabaseModule
import com.example.jetpack_multiplenavigation.uploadFileRetrofit.di.fileUploadRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initializeKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            platformModule,
            sharedModule,
            employeeModule,
            productsModule,
            networkModule,
            contactsDatabaseModule,
            studentsDatabaseModule,
            schoolDBModule,
            snackBarModule,
            downloadManageModule,
            imageViewModule,
            fileUploadRepository,
            timerServiceModule,
            chatFcmModule
        )
    }
}