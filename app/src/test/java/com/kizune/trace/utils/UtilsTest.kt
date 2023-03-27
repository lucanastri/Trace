package com.kizune.trace.utils

import com.kizune.trace.datasource.FakeLocalPlaceProvider
import com.kizune.trace.model.PlaceCategory
import org.junit.Assert.*
import org.junit.Test

class UtilsTest {

    @Test
    fun searched_text_empty_text_category_all() {
        val result = FakeLocalPlaceProvider.placesList.filterByCriteria(
            searchedText = "",
            selectedCategory = PlaceCategory.ALL
        )
        val expected = FakeLocalPlaceProvider.placesList

        assertEquals(expected, result)
    }

    @Test
    fun searched_text_not_empty_text_category_all() {
        val result = FakeLocalPlaceProvider.placesList.filterByCriteria(
            searchedText = "Caf",
            selectedCategory = PlaceCategory.ALL
        )
        val expected = FakeLocalPlaceProvider.testCase1

        assertEquals(expected, result)
    }

    @Test
    fun searched_text_empty_text_category_any() {
        val result = FakeLocalPlaceProvider.placesList.filterByCriteria(
            searchedText = "",
            selectedCategory = PlaceCategory.CAFE
        )
        val expected = FakeLocalPlaceProvider.testCase2

        assertEquals(expected, result)
    }

    @Test
    fun searched_text_not_empty_text_category_any() {
        val result = FakeLocalPlaceProvider.placesList.filterByCriteria(
            searchedText = "Caf",
            selectedCategory = PlaceCategory.CAFE
        )
        val expected = FakeLocalPlaceProvider.testCase3

        assertEquals(expected, result)
    }
}