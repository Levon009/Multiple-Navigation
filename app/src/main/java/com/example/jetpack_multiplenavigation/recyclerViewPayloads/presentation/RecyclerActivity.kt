package com.example.jetpack_multiplenavigation.recyclerViewPayloads.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jetpack_multiplenavigation.MainActivity
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.recyclerViewPayloads.presentation.fragments.RecyclerFragment

class RecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navigateFragments()
        onBackPress()
    }

    private fun navigateFragments() {
        if (supportFragmentManager.backStackEntryCount >= 1) {
            supportFragmentManager.popBackStack()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.recycler_container, RecyclerFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun onBackPress() {
        onBackPressedDispatcher.addCallback(this@RecyclerActivity, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@RecyclerActivity, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }
        })
    }
}