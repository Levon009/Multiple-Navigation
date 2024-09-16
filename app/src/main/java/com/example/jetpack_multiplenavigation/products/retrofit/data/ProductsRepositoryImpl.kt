package com.example.jetpack_multiplenavigation.products.retrofit.data

import com.example.jetpack_multiplenavigation.products.retrofit.data.model.Product
import com.example.jetpack_multiplenavigation.products.retrofit.data.model.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class ProductsRepositoryImpl(
    private val productsApi: ProductsApi
) : ProductsRepository {
    private var productsFromApi: Products? = null

    override suspend fun getProductsList(): Flow<ProductsResult<List<Product>>> {
        return flow {
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

    suspend fun getProducts(page: Int, pageSize: Int) : kotlin.Result<List<Product>> {
        delay(2000L)
        if (productsFromApi == null) delay(2000L)
        val startingIndex = page * pageSize
        return if (startingIndex + pageSize <= productsFromApi!!.products.size) {
            kotlin.Result.success(
                productsFromApi!!.products.slice(startingIndex until  startingIndex + pageSize)
            )
        } else kotlin.Result.success(emptyList())
    }
}