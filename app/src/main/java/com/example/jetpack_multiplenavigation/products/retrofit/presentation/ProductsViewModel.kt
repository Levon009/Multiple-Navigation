package com.example.jetpack_multiplenavigation.products.retrofit.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_multiplenavigation.products.retrofit.RetrofitClient
import com.example.jetpack_multiplenavigation.products.retrofit.data.ProductsApi
import com.example.jetpack_multiplenavigation.products.retrofit.data.repository.ProductsRepository
import com.example.jetpack_multiplenavigation.products.retrofit.data.repository.ProductsResult
import com.example.jetpack_multiplenavigation.products.retrofit.data.model.Post
import com.example.jetpack_multiplenavigation.products.retrofit.data.model.Product
import com.example.jetpack_multiplenavigation.products.retrofit.pagination.BasePaginationImpl
import com.example.jetpack_multiplenavigation.products.retrofit.pagination.PaginationState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ProductsViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel() {
    private val pageCount = 3

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    var state by mutableStateOf(PaginationState())
    private val basePagination = BasePaginationImpl(
        initialPage = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nexPage ->
            productsRepository.getProducts(nexPage, pageCount)
        },
        getNextPage = { items ->
            state.page + 1
        },
        onSuccess = { items, newPage ->
            state = state.copy(
                items = state.items + items,
                page = newPage,
                endReached = items.isEmpty()
            )
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        }
    )

    init {
        getProducts()
        loadNextPage()
    }

    fun loadNextPage() {
        viewModelScope.launch {
            try {
                basePagination.loadNextItem()
            } catch (e: IOException) {
                e.printStackTrace()
                _showErrorToastChannel.send(true)
            } catch (e: HttpException) {
                e.printStackTrace()
                _showErrorToastChannel.send(true)
            } catch (e: Exception) {
                e.printStackTrace()
                _showErrorToastChannel.send(true)
            }
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            productsRepository.getProductsList().collectLatest { result ->
                when(result) {
                    is ProductsResult.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is ProductsResult.Success -> {
                        result.data?.let { products ->
                            _products.update { products }
                        }
                    }
                }
            }
        }
    }

    fun getPosts(
        context: Context,
        api: ProductsApi,
        products: MutableState<List<Post>>
    ) : MutableState<List<Post>> {
        val parameters: MutableMap<String?, String?> = HashMap()
        parameters["id"] = "1"
        parameters["_sort"] = "title"
        parameters["_order"] = "desc"
        val call = api.getProducts(parameters)
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(
                call: Call<List<Post>>,
                response: Response<List<Post>>
            ) {
                if (!response.isSuccessful) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    return
                }
                products.value = response.body()!!
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return products
    }

    fun createPost(
        context: Context,
        title: MutableState<TextFieldValue>,
        text: MutableState<TextFieldValue>,
        result: MutableState<String>,
        //api: ProductsApi
    ) {
        val api: ProductsApi = RetrofitClient.postApi
        val post = Post(25, 101, title.value.text, text.value.text)
        val call = api.createProduct(post)
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    return
                }
                val responseBody: Post? = response.body()
                val content = StringBuilder("")
                content.append("Server response code: ${response.code()}\n")
                content.append("ID: ${responseBody?.id}\n")
                content.append("Title: ${responseBody?.title}\n")
                content.append("Description: ${responseBody?.text}\n\n")
                result.value = content.toString()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun updatePost(
        context: Context,
        id: MutableState<Int>,
        title: MutableState<TextFieldValue>,
        text: MutableState<TextFieldValue>,
        result: MutableState<String>,
        api: ProductsApi
    ) {
        val product = Post(25, 101, title.value.text, text.value.text)
        val call = api.putProduct(id.value, product)
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    return
                }
                val responseBody: Post? = response.body()
                val content = StringBuilder("")
                content.append("Server response code: ${response.code()}\n")
                content.append("ID: ${responseBody?.id}\n")
                content.append("Title: ${responseBody?.title}\n")
                content.append("Description: ${responseBody?.text}\n\n")
                result.value = content.toString()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun deletePost(
        context: Context,
        id: Int,
        responseCode: MutableState<String>,
        api: ProductsApi
    ) {
        val call = api.deleteProduct(id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                responseCode.value = "Response code: ${response.code()}"
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}