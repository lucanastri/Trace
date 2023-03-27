package com.kizune.trace.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kizune.trace.model.PlaceCategory

@Composable
fun TraceChipGroup(
    selectedCategory: PlaceCategory,
    onSelectionChanged: (PlaceCategory) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(enumValues<PlaceCategory>()) { index, category ->
            TraceChip(
                item = category,
                isSelected = index == selectedCategory.ordinal,
                onSelectionChanged = { selection ->
                    onSelectionChanged(selection)
                },
            )
        }
    }

}

@Composable
fun TraceChip(
    item: PlaceCategory,
    isSelected: Boolean = false,
    onSelectionChanged: (PlaceCategory) -> Unit,
) {
    Surface(
        elevation = 0.dp,
        shape = MaterialTheme.shapes.small,
        color = if (isSelected)
                    MaterialTheme.colors.primary
                else
                    MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectionChanged(item)
                    }
                )
                .padding(
                    vertical = 4.dp,
                    horizontal = 16.dp
                )
                .height(
                    intrinsicSize = IntrinsicSize.Max
                )
        ) {
            if(item.icon != null) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = stringResource(id = item.label),
                    modifier = Modifier.size(32.dp)
                )
            }
            Text(
                text = stringResource(id = item.label),
                style = MaterialTheme.typography.h6,
                color = if(isSelected)
                            MaterialTheme.colors.onPrimary
                        else
                            MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
