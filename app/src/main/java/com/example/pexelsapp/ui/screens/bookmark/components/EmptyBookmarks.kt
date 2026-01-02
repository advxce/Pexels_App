package com.example.pexelsapp.ui.screens.bookmark.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R
import com.example.pexelsapp.ui.theme.MulishBoldStyle
import com.example.pexelsapp.ui.theme.MulishMediumStyle

@Composable
fun EmptyBookmarks(
    goSearch:() -> Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.empty_txt),
            style = MulishMediumStyle
        )
        Spacer(modifier = Modifier
            .height(8.dp))
        Text(
            text = stringResource(R.string.explore),
            style = MulishBoldStyle,
            color = colorResource(R.color.defaultAppColor),
            modifier = Modifier
                .clickable{
                    goSearch()
                }
        )
    }
}