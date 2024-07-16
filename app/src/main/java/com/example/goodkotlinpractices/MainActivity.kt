package com.example.goodkotlinpractices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.goodkotlinpractices.presentation.theme.GoodKotlinPracticesTheme
import com.example.goodkotlinpractices.presentation.view.rick.RickListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoodKotlinPracticesTheme {
                RickListScreen()
            }
        }
    }
}