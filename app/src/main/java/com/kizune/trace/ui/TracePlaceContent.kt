package com.kizune.trace.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import coil.request.ImageRequest
import com.kizune.trace.R
import com.kizune.trace.model.Place

@Composable
fun PlaceContentOnly(
    place: Place,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    onBackButtonVisible: Boolean = true,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        PlaceScreenTopBar(
            place = place,
            onBackButtonVisible = onBackButtonVisible,
            onBackButtonClicked = onBackButtonClicked,
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            PlaceScreenContentImage(
                place = place,
            )
            PlaceScreenContentHeading(
                place = place,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.weight(1f))
            PlaceScreenContentBottom(
                place = place,
            )
        }
    }
}

@Composable
fun PlaceScreenTopBar(
    place: Place,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    onBackButtonVisible: Boolean = true,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = if (onBackButtonVisible) 16.dp else 32.dp
            )
    ) {
        if (onBackButtonVisible)
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
                    imageVector = Icons.Rounded.ChevronLeft,
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = stringResource(id = R.string.back_description),
                    modifier = Modifier.size(32.dp)
                )
            }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = place.name,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
fun PlaceScreenContentImage(
    place: Place,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(place.image)
                .crossfade(true)
                .build(),
            placeholder = asyncImagePlaceholder(placeholder = place.image),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun PlaceScreenContentHeading(
    place: Place,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        Row {
            Text(
                text = place.name,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.weight(1f))
            RatingBar(
                rating = place.rating,
                stars = 5,
                showUnfilledStar = true
            )
        }
        Text(
            text = stringResource(id = place.category.label),
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
                text = place.address,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSecondary
            )
            if (place.phone != null) {
                Text(
                    text = " | ",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSecondary
                )
                Text(
                    text = place.phone,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }
    }
    Text(
        text = place.description,
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

/**
 * Funzione per far partire il navigation intent con una destinazione segnaposto
 */
internal fun startNavigationIntent(
    context: Context,
    place: Place
) {
    Log.d("MyTag", place.name)
    val gmmIntentUri = Uri.parse("google.navigation:q=${place.latitude}, ${place.longitude}")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.addFlags(FLAG_ACTIVITY_NEW_TASK)
    mapIntent.setPackage("com.google.android.apps.maps")
    try {
        context.startActivity(mapIntent)
    } catch (ex: ActivityNotFoundException) {
        Toast.makeText(context, R.string.maps_not_found, Toast.LENGTH_SHORT).show()
    }

}