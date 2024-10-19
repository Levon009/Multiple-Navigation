package com.example.jetpack_multiplenavigation

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_multiplenavigation.broadcastDynamicReceiver.AirPlaneModeReceiver
import com.example.jetpack_multiplenavigation.flowSharedChannel.FlowViewModel
import com.example.jetpack_multiplenavigation.pickSaveImage.ImageViewModel
import com.example.jetpack_multiplenavigation.navigation.NavGraph
import com.example.jetpack_multiplenavigation.navigation.Routes
import com.example.jetpack_multiplenavigation.permissions.PermissionsViewModel
import com.example.jetpack_multiplenavigation.permissions.screens.MultiplePermissionRequest
import com.example.jetpack_multiplenavigation.snackBarSB.snackBar.ObserveSnackBar
import com.example.jetpack_multiplenavigation.ui.theme.JetPack_MultipleNavigationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //
    private lateinit var navController: NavHostController

    //
    private val permissionsViewModel by viewModels<PermissionsViewModel>()

    //
    private val airPlaneModeReceiver = AirPlaneModeReceiver()

    //
    private val flowViewModel = viewModels<FlowViewModel>()
    private val imageViewModel by viewModels<ImageViewModel>()
    private val tag = "Kotlin_Flows"


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        registerReceiver(
            airPlaneModeReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                flowViewModel.value.channelState.collectLatest { num ->
//                    Log.e(tag, "ChannelFlow: $num")
//                    // Go To Game Activity and return after 30 sec.
//                }
//            }
//        }
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                flowViewModel.value.shareState.collectLatest { num ->
//                    Log.e(tag, "SharedFlow: $num")
//                    // Go To Game Activity and return after 30 sec.
//                }
//            }
//        }
        setContent {
            JetPack_MultipleNavigationTheme {
                MultiplePermissionRequest(permissionsViewModel = permissionsViewModel)
                //enableEdgeToEdge()
                navController = rememberNavController()
                val snackBarHostState = remember {
                    SnackbarHostState()
                }
                ObserveSnackBar(snackBarHostState = snackBarHostState)
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackBarHostState)
                    },
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    CheckForSharingContent(navController = navController)
                    NavGraph(
                        navController = navController,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }

    private fun hideStatusBar() {
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent.getParcelableExtra(Intent.EXTRA_STREAM)
        }
        imageViewModel.updateUri(uri)
        imageViewModel.updateIsReceive(true)
    }

    @Composable
    private fun CheckForSharingContent(navController: NavHostController) {
        LaunchedEffect(key1 = imageViewModel.isReceive) {
            if (imageViewModel.isReceive) {
                delay(1000)
                navController.navigate(Routes.Intents(imageViewModel.uri.toString()))
            }
        }
    }

    fun onBackPressClicked(isBackPressed: Boolean) {
        onBackPressedDispatcher.addCallback(this@MainActivity, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle back press
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airPlaneModeReceiver)
    }
}