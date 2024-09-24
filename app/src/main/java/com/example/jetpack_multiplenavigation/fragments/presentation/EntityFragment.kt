package com.example.jetpack_multiplenavigation.fragments.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.fragments.core.util.BundleKeys
import com.example.jetpack_multiplenavigation.fragments.presentation.screens.EntityFragmentScreen

class EntityFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.entity_fragment, container, false)
        view.findViewById<ComposeView>(R.id.entityComposeView).setContent {
            val text = remember { mutableStateOf("") }
            text.value = arguments?.getString(BundleKeys.FRAGMENT_ARGUMENTS).toString()
            EntityFragmentScreen(text = text)
        }

        return view
    }
}