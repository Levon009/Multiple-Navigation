package com.example.jetpack_multiplenavigation.fragments.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jetpack_multiplenavigation.MainActivity
import com.example.jetpack_multiplenavigation.R

class FragmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragments)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        startFragments()
        onBackPress()
    }

    private fun startFragments() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, LoginFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun onBackPress() {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 1) {
                    supportFragmentManager.popBackStack()
                } else {
                    Intent(this@FragmentsActivity, MainActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
        }
    }
}