package com.example.jetpack_multiplenavigation.instagram

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.navigation.LinearLoadingAnimation
import com.example.jetpack_multiplenavigation.navigation.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun InstagramScreen(
    modifier: Modifier = Modifier,
    name: String,
    age: Int,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    var isLoading by remember {
        mutableStateOf(false)
    }
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            TopBarSection(
                name = name,
                age = age,
                navHostController = navController,
                modifier = Modifier.padding(12.dp),
            ) {
                isLoading = true
                scope.launch {
                    delay(2500L)
                    navController.navigate(Routes.LoadingScreen)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            ProfileSection()
            Spacer(modifier = Modifier.height(18.dp))
            ButtonSection(
                navController = navController,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(18.dp))
            HighLightSection(
                highLights = getHighLights(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            TabPostView(tabItems = getTabItems()) { index ->
                selectedTabIndex = index
            }
            when (selectedTabIndex) {
                0, 3 -> {
                    PostsSection(
                        posts = getPosts(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        if (isLoading) LinearLoadingAnimation(modifier = modifier)
    }
}
