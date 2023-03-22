package com.kizune.trace.utils

import android.content.Context
import com.kizune.trace.R
import java.util.*

/**
 * Stampa un saluto in base all'orario
 */
fun printGreeting(
    context: Context
): String =
    when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
        in 4..12 -> context.getString(R.string.greeting1)
        in 13..19 -> context.getString(R.string.greeting2)
        else -> context.getString(R.string.greeting3)
    }