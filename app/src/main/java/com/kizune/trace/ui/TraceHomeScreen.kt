package com.kizune.trace.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kizune.trace.R
import com.kizune.trace.model.Place
import com.kizune.trace.model.PlaceCategory
import com.kizune.trace.model.getCategory
import com.kizune.trace.theme.Heebo
import com.kizune.trace.utils.printGreeting

@Composable
fun TraceHomeScreen(
    onItemSelected: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedCategory: MutableState<PlaceCategory?> = remember { mutableStateOf(PlaceCategory.ALL) }
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.background,
        darkIcons = !isSystemInDarkTheme()
    )

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        HomeScreenHeader()
        HomeScreenSearchView(state = textState)
        TraceChipGroup(
            selectedCategory = selectedCategory.value,
            onSelectionChanged = {
                selectedCategory.value = getCategory(it)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier
                .height(1.dp)
        )
        TracePlaceList(
            searchFilter = textState,
            chipFilter = selectedCategory,
            onItemSelected = onItemSelected
        )
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
        modifier = modifier.padding(
            bottom = 32.dp,
            top = 16.dp,
            start = 16.dp,
            end = 16.dp
        )
    )
}

@Composable
fun HomeScreenSearchView(
    state: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier
) {
    TextField(
        value = state.value,
        onValueChange = {
            state.value = it
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_hint),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
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