package com.example.pexelsapp.data.network.mappers

import com.example.pexelsapp.data.network.remoteEntity.photos.PexelPageData
import com.example.pexelsapp.domain.entities.PexelPage


fun PexelPageData.toDomain():PexelPage =
    PexelPage(
        page = page,
        perPage = perPage,
        photos = photos.map { it.toDomain() }
    )