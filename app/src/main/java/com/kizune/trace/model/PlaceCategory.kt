package com.kizune.trace.model

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
    FAMILY(R.drawable.ic_family,R.string.family),
    PIZZERIA(R.drawable.ic_pizzeria, R.string.pizzeria),
    PUB(R.drawable.ic_pub, R.string.pub)
}

fun getAllCategories() : List<PlaceCategory> {
    return listOf(
        PlaceCategory.ALL,
        PlaceCategory.RESTAURANT,
        PlaceCategory.CAFE,
        PlaceCategory.PARK,
        PlaceCategory.CINEMA,
        PlaceCategory.FAMILY,
        PlaceCategory.PIZZERIA,
        PlaceCategory.PUB,
    )
}

fun getCategory(value: Int): PlaceCategory? {
    val map: Map<Int, PlaceCategory> = PlaceCategory.values().associateBy { it.label }
    return map[value]
}