package com.kizune.trace.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kizune.trace.datasource.LocalPlaceProvider
import com.kizune.trace.model.Place
import com.kizune.trace.theme.TraceTheme

@Composable
fun HomeScreen(
    contentType: ContentType,
    place: Place,
    onItemSelected: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colors.background,
        darkIcons = !isSystemInDarkTheme()
    )

    when(contentType) {
        ContentType.EXPANDED -> {
            HomeAndPlaceContent(
                place = place,
                onItemSelected = onItemSelected,
                modifier = modifier
            )
        }
        else -> {
            HomeContentOnly(
                onItemSelected = onItemSelected,
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TraceTheme {
        HomeScreen(
            place = LocalPlaceProvider.placesList[0],
            contentType = ContentType.COMPACT,
            onItemSelected = { }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, widthDp = 700)
@Composable
fun HomeScreenMediumPreview() {
    TraceTheme {
        HomeScreen(
            place = LocalPlaceProvider.placesList[0],
            contentType = ContentType.MEDIUM,
            onItemSelected = { }
        )
    }
}

@Preview(showBackground = true, widthDp = 1200, heightDp = 800)
@Composable
fun HomeScreenxtendedPreview() {
    TraceTheme {
        HomeScreen(
            place = LocalPlaceProvider.placesList[0],
            contentType = ContentType.EXPANDED,
            onItemSelected = { }
        )
    }
}