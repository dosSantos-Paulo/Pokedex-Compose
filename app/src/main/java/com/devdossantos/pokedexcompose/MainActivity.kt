package com.devdossantos.pokedexcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivity(name = "Paulo")
        }
    }
}

@Composable
fun MainActivity(name: String) {
    Text(text = "Hello $name!")
}