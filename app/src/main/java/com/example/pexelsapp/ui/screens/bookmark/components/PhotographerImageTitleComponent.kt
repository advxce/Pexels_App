package com.example.pexelsapp.ui.screens.bookmark.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R
import com.example.pexelsapp.ui.theme.MulishMediumStyle

@Composable
fun PhotographerImageTitleComponent(
    photographer:String,
){

        Text(
            text = "$photographer",
            color = colorResource(R.color.authorSectionTextColor),
            style = MulishMediumStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.authorSectionColor),
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
                .padding(horizontal = 8.dp, vertical = 6.dp)
        )
}