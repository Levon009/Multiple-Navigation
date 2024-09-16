package com.example.jetpack_multiplenavigation

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.di.AppModule
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.di.AppModuleImpl
import com.example.jetpack_multiplenavigation.dependencies.di.initializeKoin
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext

@HiltAndroidApp
class BaseApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@BaseApplication)
        }

        appModule = AppModuleImpl(this)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.03)
                    .directory(cacheDir)
                    .build()
            }
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.1)
                    .strongReferencesEnabled(false)
                    .build()
            }
            .logger(DebugLogger())
            .build()
    }

    companion object {
        lateinit var appModule: AppModule
    }
}