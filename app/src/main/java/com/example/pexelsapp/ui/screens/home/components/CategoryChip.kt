package com.example.pexelsapp.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R
import com.example.pexelsapp.ui.theme.MulishMediumStyle

@Composable
fun CategoryChip(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit
) {
    Surface(
        color = if (isActive)
            colorResource(R.color.defaultAppColor)
        else
            colorResource(R.color.itemsBackground),
        shape = CircleShape,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = if (isActive)
                colorResource(R.color.white)
            else
                colorResource(R.color.chipColorText),
            style = MulishMediumStyle,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        )
    }
}