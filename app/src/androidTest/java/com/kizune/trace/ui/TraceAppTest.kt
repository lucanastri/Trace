package com.kizune.trace.ui

import com.kizune.trace.R
import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.kizune.trace.datasource.LocalPlaceProvider
import com.kizune.trace.model.Place
import com.kizune.trace.model.PlaceViewModel
import com.kizune.trace.theme.TraceTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TraceAppTest {
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun basic_navigation_to_place_screen() {
        launchStartingScreen()
        with(composeTestRule) {
            onNodeWithText(context.getString(R.string.login)).performClick()
            onNodeWithText(context.getString(R.string.search_hint)).assertIsDisplayed()
            onNodeWithText(LocalPlaceProvider.placesList[0].name).assertIsDisplayed()
            onNodeWithText(LocalPlaceProvider.placesList[0].name).performClick()
            onNodeWithText(context.getString(R.string.launch_maps)).assertIsDisplayed()
        }
    }

    @Test
    fun basic_navigation_fill_searchview_results() {
        launchStartingScreen()
        with(composeTestRule) {
            onNodeWithText(context.getString(R.string.login)).performClick()
            onNodeWithTag("search_view").performTextInput("caf")
            onNodeWithText(LocalPlaceProvider.placesList[0].name).assertIsDisplayed()
        }
    }

    @Test
    fun basic_navigation_fill_searchview_error() {
        launchStartingScreen()
        with(composeTestRule) {
            onNodeWithText(context.getString(R.string.login)).performClick()
            onNodeWithTag("search_view").performTextInput("caf")
            onNodeWithText(LocalPlaceProvider.placesList[9].name).assertDoesNotExist()
        }
    }

    private fun launchStartingScreen() {
        composeTestRule.setContent {
            TraceTheme {
                val navController = rememberNavController()
                val viewModel: PlaceViewModel = viewModel()
                Scaffold { innerPadding ->
                    val uiState by viewModel.uiState.collectAsState()
                    val place: Place = uiState.place ?: LocalPlaceProvider.placesList[0]

                    NavHost(
                        navController = navController,
                        startDestination = TraceScreen.Start.name,
                        modifier = Modifier.padding(innerPadding)
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
                                contentType = ContentType.COMPACT,
                                place = place,
                                onItemSelected = { item ->
                                    viewModel.updatePlace(item)
                                    if(true)
                                        navController.navigate(TraceScreen.Place.name)
                                },
                            )
                        }

                        composable(route = TraceScreen.Place.name) {
                            PlaceScreen(
                                place = place,
                                onBackButtonClicked = { navController.navigateUp() },
                            )
                        }
                    }
                }
            }
        }
    }
}