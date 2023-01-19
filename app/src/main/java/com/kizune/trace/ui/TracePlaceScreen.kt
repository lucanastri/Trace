package com.kizune.trace.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kizune.trace.R
import com.kizune.trace.datasource.LocalPlaceProvider.placesList
import com.kizune.trace.model.Place
import com.kizune.trace.model.PlaceUiState

@Composable
fun TracePlaceScreen(
    placeUiState: PlaceUiState,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val place: Place = placeUiState.place ?: placesList[0]
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TracePlaceScreenTopBar(
            placeUiState = placeUiState,
            onBackButtonClicked = onBackButtonClicked,
        )
        PlaceScreenContentImage(
            placeUiState = placeUiState
        )
        PlaceScreenContentHeading(
            placeUiState = placeUiState
        )
        Spacer(modifier = Modifier.weight(1f))
        PlaceScreenContentBottom(
            place = place,
        )
    }
}

@Composable
fun TracePlaceScreenTopBar(
    placeUiState: PlaceUiState,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 24.dp
            )
    ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(50)
                )
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.ChevronLeft ,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = stringResource(id = R.string.back_description),
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = placeUiState.place?.name ?: "",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
fun PlaceScreenContentImage(
    placeUiState: PlaceUiState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = placeUiState.place?.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(328.dp)
                .clip(RoundedCornerShape(24.dp))
        )
    }
}

@Composable
fun PlaceScreenContentHeading(
    placeUiState: PlaceUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp)) {
        Row {
            Text(
                text = placeUiState.place?.name ?: "",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.weight(1f))
            RatingBar(
                rating = placeUiState.place?.rating ?: 4.0,
                stars = 5,
                showUnfilledStar = true
            )
        }
        Text(
            text = stringResource(id = placeUiState.place?.category ?: R.string.pub),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(top = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                text = placeUiState.place?.address ?: "",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSecondary
            )
            if(placeUiState.place?.phone != null) {
                Text(
                    text = " | ",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSecondary
                )
                Text(
                    text = placeUiState.place.phone,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }
    }
    Text(
        text = placeUiState.place?.description ?: "",
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Justify,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )
}

@Composable
fun PlaceScreenContentBottom(
    place: Place,
    modifier: Modifier = Modifier
) {
    val context: Context = LocalContext.current
    TraceButton(
        onClick = { startNavigationIntent(context, place) },
        text = R.string.launch_maps,
        modifier = modifier
            .fillMaxWidth()
    )
}

private fun startNavigationIntent(
    context: Context,
    place: Place
) {
    val gmmIntentUri = Uri.parse("google.navigation:q=40.686806972867004, 14.805822890660826")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    mapIntent.resolveActivity(context.packageManager)?.let {
        context.startActivity(mapIntent)
    }
}
