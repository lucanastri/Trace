package com.kizune.trace.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kizune.trace.model.PlaceViewModel

enum class TraceScreen {
    Start,
    Home,
    Place
}

/**
 * Entry point dell'app per permettere la navigazione tra i vari screen
 */

@Composable
fun TraceApp(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val viewModel: PlaceViewModel = viewModel()

    BackHandler{
        onBackClicked()
    }

    Scaffold { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

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
                TraceHomeScreen(
                    onItemSelected = { item ->
                        viewModel.updatePlace(item)
                        navController.navigate(TraceScreen.Place.name)
                    }
                )
            }

            composable(route = TraceScreen.Place.name) {
                TracePlaceScreen(
                    placeUiState = uiState,
                    onBackButtonClicked = { navController.navigateUp() },
                )
            }
        }
    }
}
