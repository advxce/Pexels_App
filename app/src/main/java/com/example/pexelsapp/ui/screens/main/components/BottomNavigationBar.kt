package com.example.pexelsapp.ui.screens.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R

@Composable
fun BottomNavigationBar(
    selectedItem:Int,
    onItemSelected:(Int) -> Unit,
    modifier: Modifier = Modifier
){
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            BottomNavItem(
                enableIcon = ImageVector.vectorResource(R.drawable.enable_home),
                unableIcon = ImageVector.vectorResource(R.drawable.unable_home),
                isSelected = selectedItem == 0,
                onClick = { onItemSelected(0) },
                modifier = Modifier.weight(1f)
            )
            BottomNavItem(
                enableIcon = ImageVector.vectorResource(R.drawable.enable_bookmark),
                unableIcon = ImageVector.vectorResource(R.drawable.unable_bookmark),
                isSelected = selectedItem == 1,
                onClick = { onItemSelected(1) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}