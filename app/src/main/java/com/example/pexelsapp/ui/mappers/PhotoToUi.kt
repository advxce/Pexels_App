package com.example.pexelsapp.ui.mappers

import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.ui.entities.PhotoUi

fun Photo.toUi(): PhotoUi =
    PhotoUi(
        id = id,
        src = src,
        photographer = photographer,
        bookmarked = bookmarked
    )