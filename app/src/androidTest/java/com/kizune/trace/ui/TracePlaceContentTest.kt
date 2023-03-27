package com.kizune.trace.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.kizune.trace.datasource.LocalPlaceProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TracePlaceContentTest {
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun google_maps_intent_test() {
        startNavigationIntent(
            context = context,
            place = LocalPlaceProvider.placesList[0]
        )
    }
}