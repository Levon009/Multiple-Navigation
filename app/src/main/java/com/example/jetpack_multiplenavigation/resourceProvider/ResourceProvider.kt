package com.example.jetpack_multiplenavigation.resourceProvider

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes resId: Int): String
}