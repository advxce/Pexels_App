package com.example.pexelsapp.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R
import com.example.pexelsapp.ui.entities.PhotoUi

@Composable
fun BookmarkComponent(
    isBookmarked: Boolean,
    onClick: () -> Unit
) {


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(48.dp)
            .background(
                color = colorResource(R.color.itemsBackground),
                shape = CircleShape
            )
            .clickable{onClick()}

    ) {
        Icon(
            imageVector = if (isBookmarked)
                ImageVector.vectorResource(R.drawable.enable_bookmark)
            else
                ImageVector.vectorResource(R.drawable.unable_bookmark),
            tint = if (isBookmarked)
                colorResource(R.color.defaultAppColor)
            else
                colorResource(R.color.tintBookmark),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
        )
    }
}