package com.dev.ecomersapp.presentation.ui.features.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryFilter(modifier: Modifier = Modifier, categories: List<String>,
                   onCategorySelected: (String?) -> Unit) {

    LazyRow(modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item {
            AssistChip(
                onClick = { onCategorySelected(null) },
                label = { Text("All") }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        items(categories) { category ->
            AssistChip(
                onClick = { onCategorySelected(category) },
                label = { Text(category) }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

    }


    
}