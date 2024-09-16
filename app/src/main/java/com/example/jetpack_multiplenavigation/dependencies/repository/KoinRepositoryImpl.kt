package com.example.jetpack_multiplenavigation.dependencies.repository

import com.example.jetpack_multiplenavigation.dependencies.data.DbClient

class KoinRepositoryImpl(
    private val dbClient: DbClient
) : KoinRepository {
    override fun helloWorld(): String {
        return "Hello World!!!"
    }
}