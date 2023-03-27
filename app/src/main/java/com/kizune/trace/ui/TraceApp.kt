package com.kizune.trace.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kizune.trace.datasource.LocalPlaceProvider
import com.kizune.trace.model.Place
import com.kizune.trace.model.PlaceViewModel

enum class ContentType {
    COMPACT,
    MEDIUM,
    EXPANDED,
}

enum class TraceScreen {
    Start,
    Home,
    Place
}

@Composable
fun TraceApp(
    onBackClicked: () -> Unit,
    windowWidthSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val viewModel: PlaceViewModel = viewModel()

    BackHandler{
        onBackClicked()
    }

    val contentType: ContentType = when(windowWidthSize) {
        WindowWidthSizeClass.Compact -> ContentType.COMPACT
        WindowWidthSizeClass.Medium -> ContentType.MEDIUM
        WindowWidthSizeClass.Expanded -> ContentType.EXPANDED
        else -> ContentType.COMPACT
    }

    Scaffold { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        val place: Place = uiState.place ?: LocalPlaceProvider.placesList[0]

        NavHost(
            navController = navController,
            startDestination = TraceScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = TraceScreen.Start.name) {
                TraceStartScreen(
                    onLoginButtonClicked = {
                        navController.popBackStack()
                        navController.navigate(TraceScreen.Home.name)
                    },
                )
            }

            composable(route = TraceScreen.Home.name) {
                HomeScreen(
                    contentType = contentType,
                    place = place,
                    onItemSelected = { item ->
                        viewModel.updatePlace(item)
                        if(contentType != ContentType.EXPANDED)
                            navController.navigate(TraceScreen.Place.name)
                    },
                    modifier = Modifier.systemBarsPadding()
                )
            }

            composable(route = TraceScreen.Place.name) {
                PlaceScreen(
                    place = place,
                    onBackButtonClicked = { navController.navigateUp() },
                    modifier = Modifier.systemBarsPadding()
                )
            }
        }
    }
}
