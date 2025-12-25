package com.example.pexelsapp.ui.mappers

import com.example.pexelsapp.domain.entities.CollectionDomain
import com.example.pexelsapp.ui.entities.CollectionUi

fun CollectionDomain.toUi(): CollectionUi =
    CollectionUi(
        id = id,
        title = title
    )