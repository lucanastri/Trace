package com.kizune.trace.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kizune.trace.R
import com.kizune.trace.datasource.LocalPlaceProvider.placesList
import com.kizune.trace.model.Place
import com.kizune.trace.model.PlaceCategory

fun filteredList(
    items: List<Place>,
    searchedText: String,
    selectedCategory: Int
): List<Place> {
    val filteredItems = if (searchedText.isEmpty()) {
        placesList.filter { place ->
            place.category == enumValues<PlaceCategory>()[selectedCategory].label
                    || enumValues<PlaceCategory>()[selectedCategory].label == R.string.all
        }
    } else {
        val resultList = ArrayList<Place>()
        for (item in items) {
            if (item.name.lowercase().contains(searchedText.lowercase())) {
                resultList.add(item)
            }
        }
        resultList.filter { place ->
            place.category == enumValues<PlaceCategory>()[selectedCategory].label
                    || enumValues<PlaceCategory>()[selectedCategory].label == R.string.all
        }
    }
    return filteredItems
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlaceItem(
    item: Place,
    onItemSelected: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {
            onItemSelected(item)
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.image)
                    .crossfade(true)
                    .build(),
                placeholder = asyncImagePlaceholder(placeholder = item.image),
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = stringResource(id = item.category),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                RatingBar(
                    rating = item.rating,
                    stars = 4,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}