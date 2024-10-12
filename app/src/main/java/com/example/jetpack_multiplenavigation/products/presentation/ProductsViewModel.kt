package com.example.jetpack_multiplenavigation.products.presentation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_multiplenavigation.products.domain.data.model.Post
import com.example.jetpack_multiplenavigation.products.domain.data.model.Product
import com.example.jetpack_multiplenavigation.products.domain.use_cases.ProductsUseCases
import com.example.jetpack_multiplenavigation.products.presentation.pagination.BasePaginationImpl
import com.example.jetpack_multiplenavigation.products.presentation.pagination.PaginationState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productsUseCases: ProductsUseCases,
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
            productsUseCases.getProducts.getProducts(nexPage, pageCount)
        },
        getNextPage = { _ ->
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
            } catch (e: Exception) {
                e.printStackTrace()
                _showErrorToastChannel.send(true)
            }
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            _products.value = productsUseCases.getProducts(errorChannel = _showErrorToastChannel)
            Log.e("LLL", "${_products.value == null}")
        }
    }

    fun getPosts(
        context: Context,
        posts: MutableState<List<Post>>
    ) = productsUseCases.getPosts(
        context = context,
        posts = posts
    )

    fun createPost(
        context: Context,
        title: MutableState<TextFieldValue>,
        text: MutableState<TextFieldValue>,
        result: MutableState<String>,
    ) = productsUseCases.cratePost(
        context = context,
        title = title,
        text = text,
        result = result
    )

    fun updatePost(
        context: Context,
        id: MutableState<Int>,
        title: MutableState<TextFieldValue>,
        text: MutableState<TextFieldValue>,
        result: MutableState<String>
    ) = productsUseCases.updatePost(
        context = context,
        id = id,
        title = title,
        text = text,
        result = result
    )

    fun deletePost(
        context: Context,
        id: Int,
        responseCode: MutableState<String>
    ) = productsUseCases.deletePost(
        context = context,
        id = id,
        responseCode = responseCode
    )
}