package com.example.jetpack_multiplenavigation.resourceProvider

import android.content.Context

class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {
    override fun getString(resId: Int) = context.getString(resId)
}