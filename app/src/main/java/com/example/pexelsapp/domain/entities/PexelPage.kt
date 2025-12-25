package com.example.pexelsapp.domain.entities

data class PexelPage(
    val page: Int,
    val perPage: Int,
    val photos: List<Photo>
)
