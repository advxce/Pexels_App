package com.example.pexelsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R
import com.example.pexelsapp.ui.entities.CollectionUi
import com.example.pexelsapp.ui.theme.MulishMediumStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAndCategoriesBar(
    categories: List<CollectionUi>?,
    isLoading: Boolean,
    onSearch: (String) -> Unit,
    onClear: () -> Unit
) {
    var query by remember { mutableStateOf(TextFieldValue("")) }

    var activeCategory by remember { mutableStateOf<CollectionUi?>(null) }

    val originalOrder = remember(categories) { categories ?: emptyList() }

    val orderedCategories = remember(activeCategory, categories) {
        if (activeCategory == null) {
            originalOrder
        } else {
            listOf(activeCategory!!) + originalOrder.filter { it.id != activeCategory?.id }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(colorResource(R.color.itemsBackground), CircleShape)
                .clip(CircleShape),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = rememberVectorPainter(
                        image = ImageVector.vectorResource(R.drawable.search)
                    ),
                    tint = colorResource(R.color.defaultAppColor),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onSearch(query.text)
                            if (query.text.isEmpty()) onSearch("")
                        }
                )

                Spacer(Modifier.width(10.dp))

                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                    BasicTextField(
                        value = query.text,
                        onValueChange = { newText ->
                            query = TextFieldValue(newText)
                            onSearch(newText)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            if (query.text.isEmpty()) {
                                Text(
                                    stringResource(R.string.searchHint),
                                    color = colorResource(R.color.unableItemColor),
                                    style = MulishMediumStyle
                                )
                            }
                            innerTextField()
                        }
                    )
                }

                if (query.text.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                        tint = colorResource(R.color.defaultAppColor),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                query = TextFieldValue("")
                                onClear()
                                activeCategory = null
                            }
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        if (orderedCategories.isNotEmpty()) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(orderedCategories, key = { it.id }) { collection ->
                    val isActive = collection == activeCategory

                    CategoryChip(
                        text = collection.title,
                        isActive = isActive,
                        onClick = {
                            if (isActive) {
                                activeCategory = null
                                onSearch("")
                            } else {
                                activeCategory = collection
                                query = TextFieldValue(collection.title)
                                onSearch(collection.title)
                            }
                        }
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        if (isLoading) {
            Spacer(Modifier.height(8.dp))
            ProgressIndicatorComponent()
        }
    }
}

