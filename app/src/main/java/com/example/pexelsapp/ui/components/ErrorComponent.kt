package com.example.pexelsapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorComponent(error:String){
    Text(text = "Ошибка: $error")
}