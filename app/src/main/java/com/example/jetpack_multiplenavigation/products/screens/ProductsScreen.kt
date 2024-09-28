package com.example.jetpack_multiplenavigation.products.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.jetpack_multiplenavigation.navigation.Routes
import com.example.jetpack_multiplenavigation.products.retrofit.data.model.Product
import com.example.jetpack_multiplenavigation.products.retrofit.presentation.ProductsViewModel
import com.example.jetpack_multiplenavigation.scrollToTopButton.ScrollToTopButton
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun ProductsScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val productsViewModel = koinViewModel<ProductsViewModel>()
    val products = productsViewModel.products.collectAsState().value
    val state = productsViewModel.state
    LaunchedEffect(key1 = productsViewModel.showErrorToastChannel) {
        productsViewModel.showErrorToastChannel.collectLatest { show ->
            if (show) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            ScrollToTopButton(state = lazyListState)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Products List",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                 navigationIcon = {
                     IconButton(onClick = {
                         navController.navigateUp()
                     }) {
                         Icon(
                             imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                             contentDescription = "Arrow back"
                         )
                     }
                 },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (products.isEmpty()) {
                LoadingContent(modifier = Modifier.fillMaxSize())
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize() 
                ) {
                    LazyColumn(
                        state = lazyListState,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.94f)

                    ) {
                        items(state.items.size) { i ->
                            val product = state.items[i]
                            if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                                productsViewModel.loadNextPage()
                            }

                            ProductContent(product = (product as Product)) {
                                navController.navigate(Routes.PostProducts(0)) {
                                    popUpTo<Routes.HomeScreen> {
                                        inclusive = false
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    if (state.isLoading && state.items.size > 1) {
                        LoadingContent(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductContent(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(16.dp)
            .clickable {
                onClick()
            }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.08f))
                .padding(10.dp)
        ) {
            item {
                ImageContent(product = product)
                Spacer(modifier = Modifier.height(6.dp))
            }
            item {
                InfoContent(product = product)
            }
        }
    }
}

@Composable
fun ImageContent(product: Product) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(product.thumbnail)
            .size(Size.ORIGINAL)
            .build()
    ).state

    if (imageState is AsyncImagePainter.State.Error || imageState is AsyncImagePainter.State.Loading) {
        LoadingContent(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
    if (imageState is AsyncImagePainter.State.Success) {
        Image(
            painter = imageState.painter,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}

@Composable
fun InfoContent(product: Product) {
    Text(
        text = "${product.title} -- Price: ${product.price}",
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Serif,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = product.description,
        fontSize = 13.sp,
        fontFamily = FontFamily.Serif,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(30.dp),
            color = Color.Magenta
        )
    }
}