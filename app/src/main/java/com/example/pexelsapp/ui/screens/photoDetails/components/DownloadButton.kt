package com.example.pexelsapp.ui.screens.photoDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R
import com.example.pexelsapp.ui.theme.MulishMediumStyle

@Composable
fun DownloadButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(

        modifier = modifier
            .height(48.dp)
            .clickable { onClick() }
            .background(
                color = colorResource(R.color.itemsBackground),
                shape = RoundedCornerShape(24.dp)
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = colorResource(R.color.defaultAppColor),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.download_icon),
                    contentDescription = stringResource(R.string.downloadBtnDescription),
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier
                    .padding(start = 8.dp, end = 40.dp),
                text = stringResource(R.string.download),
                style = MulishMediumStyle,
                fontWeight = FontWeight.W600,
                color = colorResource(R.color.chipColorText)
            )
        }
    }
}