package com.kizune.trace.ui

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.kizune.trace.R
import kotlin.math.floor

/**
 * This is the rating bar composed by 5 stars
 */
@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier,
    stars: Int = 5,
    starsColor: Color = colorResource(id = R.color.star_filled),
    showUnfilledStar: Boolean = false
) {
    val filledStars = if(rating > stars) stars else floor(rating).toInt()
    val unfilledStars = if(rating >= 0) stars - filledStars else stars

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier
                    .size(24.dp)
                    .testTag("${it+1}_filled")
            )
        }

        if (showUnfilledStar) {
            repeat(unfilledStars) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
                        .size(24.dp)
                        .testTag("${it+1}_unfilled")
                )
            }
        }
    }
}