package com.example.jetpack_multiplenavigation.products.data.repository

import com.example.jetpack_multiplenavigation.products.data.retrofit.data.ProductsApi
import com.example.jetpack_multiplenavigation.products.domain.data.model.Products
import com.example.jetpack_multiplenavigation.products.domain.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class ProductsRepositoryImpl(
    private val productsApi: ProductsApi
) : ProductsRepository {
    private var productsFromApi: Products? = null

    override suspend fun getProductsList() = flow {
        productsFromApi = try {
            productsApi.getProductsList()
        } catch (e: IOException) {
            e.printStackTrace()
            emit(ProductsResult.Error(message = "Error (IO) loading products"))
            return@flow
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(ProductsResult.Error(message = "Error (HTTP) loading products"))
            return@flow
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ProductsResult.Error(message = "Error (Exception) loading products"))
            return@flow
        }

        emit(ProductsResult.Success(productsFromApi!!.products))
    }.flowOn(Dispatchers.IO)
}