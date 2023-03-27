package com.kizune.trace.utils

import com.kizune.trace.model.Place
import com.kizune.trace.model.PlaceCategory

/**
 * Function that performs filtering by query and
 * selected category
 *
 * test-case:
 * - searchedText is empty and selectedCategory is ALL
 * - searchedText is not empty and selectedCategory is ALL
 * - searchedText is empty and selectedCategory is any value
 * - searchedText is not empty and selectedCategory is any value
 */
fun List<Place>.filterByCriteria(
    searchedText: String,
    selectedCategory: PlaceCategory
): List<Place> {
    if(searchedText.isBlank() && selectedCategory == PlaceCategory.ALL) {
        return this
    }

    if(searchedText.isBlank()) {
        return this.filter { place ->
            place.category == selectedCategory || selectedCategory == PlaceCategory.ALL
        }
    }
    if (selectedCategory == PlaceCategory.ALL) {
        return this.filter { place ->
            place.name.contains(searchedText, true)
        }
    }

    return this.filter { place ->
        place.name.contains(searchedText, true) && selectedCategory == place.category
    }
}