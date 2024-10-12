package com.example.jetpack_multiplenavigation.products.domain.use_cases

import com.example.jetpack_multiplenavigation.products.data.repository.ProductsResult
import com.example.jetpack_multiplenavigation.products.domain.data.model.Product
import com.example.jetpack_multiplenavigation.products.domain.repository.ProductsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

class GetProducts(
    private val repository: ProductsRepository
) {
    private var products: List<Product>? = null

    suspend operator fun invoke(errorChannel: Channel<Boolean>) : List<Product> {
        repository.getProductsList().collectLatest { result ->
            when(result) {
                is ProductsResult.Error -> {
                    errorChannel.send(true)
                }
                is ProductsResult.Success -> {
                    result.data?.let { products ->
                        this.products = products
                    }
                }
            }
        }

        return products!!
    }

    suspend fun getProducts(page: Int, pageSize: Int) : Result<List<Product>> {
        delay(2000L)
        if (products == null) delay(2000L)
        val startingIndex = page * pageSize
        return if (startingIndex + pageSize <= products!!.size) {
            Result.success(
                products!!.slice(startingIndex until  startingIndex + pageSize)
            )
        } else Result.success(emptyList())
    }
}