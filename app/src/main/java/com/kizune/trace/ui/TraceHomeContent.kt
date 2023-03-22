package com.kizune.trace.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizune.trace.R
import com.kizune.trace.datasource.LocalPlaceProvider
import com.kizune.trace.model.Place
import com.kizune.trace.theme.Heebo
import com.kizune.trace.utils.printGreeting

@Composable
fun HomeContentOnly(
    onItemSelected: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val items by remember { mutableStateOf(LocalPlaceProvider.placesList) }

    var selectedCategory by rememberSaveable { mutableStateOf(0) }
    var textFilter by rememberSaveable { mutableStateOf("") }

    val filteredList = filteredList(
        items = items,
        selectedCategory = selectedCategory,
        searchedText = textFilter
    )

    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            HomeScreenHeader(modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            HomeScreenSearchView(
                value = textFilter,
                onValueChange = { value ->
                    textFilter = value
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        item {
            TraceChipGroup(
                selectedCategory = selectedCategory,
                onSelectionChanged = { index ->
                    selectedCategory = index
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(filteredList) { place ->
            PlaceItem(
                item = place,
                onItemSelected = onItemSelected,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun HomeScreenHeader(
    modifier: Modifier = Modifier
) {
    Text(
        text = printGreeting(LocalContext.current),
        style = MaterialTheme.typography.h4,
        color = MaterialTheme.colors.onSurface,
        modifier = modifier
    )
}

@Composable
fun HomeScreenSearchView(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_hint),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary
            )
        },
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        modifier = modifier
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(id = R.string.search_description),
                modifier = Modifier.size(32.dp)
            )
        },
        textStyle = TextStyle(
            fontFamily = Heebo,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        ),
        singleLine = true,
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.surface,
            leadingIconColor = MaterialTheme.colors.onSurface,
            cursorColor = MaterialTheme.colors.onSurface,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            placeholderColor = MaterialTheme.colors.onSecondary
        )
    )
}

@Composable
fun HomeAndPlaceContent(
    place: Place,
    onItemSelected: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {
        HomeContentOnly(
            onItemSelected = onItemSelected,
            modifier = Modifier.weight(0.5f)
        )
        PlaceContentOnly(
            place = place,
            onBackButtonVisible = false,
            onBackButtonClicked = { },
            modifier = Modifier.weight(0.5f)
        )
    }
}
