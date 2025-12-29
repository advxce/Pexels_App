package com.example.pexelsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import com.example.pexelsapp.R
import com.example.pexelsapp.ui.theme.MulishBoldStyle

@Composable
fun NetworkStub(
    tryConnect: ()->Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.no_nertwork_icon),
            contentDescription = stringResource(R.string.no_connection),
        )
        Text(
            text = "Try Again",
            color = colorResource(R.color.defaultAppColor),
            style = MulishBoldStyle,
            fontSize = 18.sp,
            modifier = Modifier
                .clickable{
                    tryConnect()
                }
        )
    }
}