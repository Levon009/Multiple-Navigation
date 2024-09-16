package com.example.jetpack_multiplenavigation.downloadManager.di

import com.example.jetpack_multiplenavigation.downloadManager.Downloader
import com.example.jetpack_multiplenavigation.downloadManager.DownloaderImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val downloadManageModule = module {
    singleOf(::DownloaderImpl).bind<Downloader>()
}