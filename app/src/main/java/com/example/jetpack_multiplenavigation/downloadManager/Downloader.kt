package com.example.jetpack_multiplenavigation.downloadManager

interface Downloader {
    fun downloadFile(url: String) : Long
}