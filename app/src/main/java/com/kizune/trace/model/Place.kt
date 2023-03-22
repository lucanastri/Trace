package com.kizune.trace.model

import android.location.Location
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.kizune.trace.R

enum class PlaceCategory(
    @DrawableRes val icon: Int?,
    @StringRes val label: Int
) {
    ALL(null, R.string.all),
    RESTAURANT(R.drawable.ic_restaurant, R.string.restaurant),
    CAFE(R.drawable.ic_cafe, R.string.cafe),
    PARK(R.drawable.ic_park, R.string.park),
    CINEMA(R.drawable.ic_cinema, R.string.cinema),
    FAMILY(R.drawable.ic_family, R.string.family),
    PIZZERIA(R.drawable.ic_pizzeria, R.string.pizzeria),
    PUB(R.drawable.ic_pub, R.string.pub)
}

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
