package com.example.pexelsapp.ui.mappers

import com.example.pexelsapp.domain.entities.PexelPage
import com.example.pexelsapp.ui.entities.PexelPageUi

fun PexelPage.toUi(): PexelPageUi =
    PexelPageUi(
        page = page,
        perPage = perPage,
        photos = photos.map { it.toUi() }
    )