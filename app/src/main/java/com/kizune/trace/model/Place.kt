package com.kizune.trace.model

import android.location.Location
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    @DrawableRes val image: Int,
    val name: String,
    @StringRes val category: Int,
    val address: String,
    val phone: String?,
    val description: String,
    val location: Location,
    val rating: Double
)
