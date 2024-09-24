package com.example.jetpack_multiplenavigation.fragments.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.fragments.presentation.screens.LoginScreen

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        view.findViewById<ComposeView>(R.id.loginComposeView).setContent {
            LoginScreen()
        }

        return view
    }
}