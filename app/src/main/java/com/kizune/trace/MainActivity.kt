package com.kizune.trace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.colorResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kizune.trace.theme.TraceTheme
import com.kizune.trace.ui.TraceApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TraceTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(
                    color = colorResource(id = R.color.blue),
                    darkIcons = false
                )

                TraceApp(
                    onBackClicked = { moveTaskToBack(true) }
                )
            }
        }
    }
}