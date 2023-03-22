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
fun PlaceScreen(
    place: Place,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colors.background,
        darkIcons = !isSystemInDarkTheme()
    )

    PlaceContentOnly(
        place = place,
        onBackButtonClicked = onBackButtonClicked,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PlaceScreenPreview() {
    TraceTheme {
        PlaceScreen(
            place = LocalPlaceProvider.placesList[0],
            onBackButtonClicked = { }
        )
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun PlaceScreenMediumPreview() {
    TraceTheme {
        PlaceScreen(
            place = LocalPlaceProvider.placesList[0],
            onBackButtonClicked = { }
        )
    }
}
