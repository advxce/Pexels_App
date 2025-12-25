package com.example.pexelsapp.domain.entities

data class Photo(
    val id:Int,
    val src: String,
    val bookmarked: Boolean = false
)
