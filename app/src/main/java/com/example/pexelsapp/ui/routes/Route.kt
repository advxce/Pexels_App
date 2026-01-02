package com.example.pexelsapp.ui.routes

sealed class Route(val route:String) {
    data object Home : Route("home")
    data object Bookmarks: Route("bookmarks")
    data object Details :Route("details/{photoId}"){
        fun create(photoId:Int) = "details/$photoId"
        const val PHOTO_ID = "photoId"
    }
}