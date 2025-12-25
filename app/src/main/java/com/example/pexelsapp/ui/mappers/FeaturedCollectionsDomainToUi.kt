package com.example.pexelsapp.ui.mappers

import com.example.pexelsapp.data.network.remoteEntity.featuredCollections.FeaturedCollectionsData
import com.example.pexelsapp.domain.entities.FeaturedCollections
import com.example.pexelsapp.ui.entities.FeaturedCollectionsUi

fun FeaturedCollections.toUi(): FeaturedCollectionsUi =
    FeaturedCollectionsUi(
        collections = collections.map { it.toUi() }
    )