package com.sherry.universitycatalogapp.ui.university

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sherry.universitycatalogapp.R
import com.sherry.universitycatalogapp.data.model.University
import com.sherry.universitycatalogapp.ui.core.component.InternetConnectionAlertDialog

private val dividerThickness = 1.dp

@Composable
fun UniversityScreen(
    modifier: Modifier = Modifier,
    universityViewModel: UniversityViewModel = hiltViewModel(),
) {
    val universityUiState: UniversityUiState by universityViewModel.toastUiState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier
    ) {
        handleToastUiState(modifier = modifier, uiState = universityUiState)
    }
}

private fun LazyListScope.handleToastUiState(
    uiState: UniversityUiState,
    modifier: Modifier,
) {
    when (uiState) {
        is UniversityUiState.Loading -> progressIndicator(modifier = modifier)

        is UniversityUiState.Success -> newUniversityItem(uiState.universities)

        is UniversityUiState.Error -> errorAlertDialog(modifier = modifier, uiState.error)
    }
}

private fun LazyListScope.progressIndicator(modifier: Modifier = Modifier) = item {
    Box(
        modifier = modifier.fillParentMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

private fun LazyListScope.newUniversityItem(universities: List<University>) {
    itemsIndexed(universities) { index, item ->
        UniversityItem(
            name = item.name,
            country = item.country,
            webpageUrl = item.webpageUrl
        )

        if (index < universities.lastIndex)
            Divider(color = Color.LightGray, thickness = dividerThickness)
    }
}

private fun LazyListScope.errorAlertDialog(
    modifier: Modifier,
    error: String?,
) {
    item {
        var openAlertDialog by rememberSaveable { mutableStateOf(true) }
        Box(
            modifier = modifier.fillParentMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.no_data_found),
            )
        }

        when {
            openAlertDialog -> {
                InternetConnectionAlertDialog(
                    errorMessage = error ?: stringResource(id = R.string.no_network),
                    onDismissRequest = {
                        openAlertDialog = false
                    },
                    onConfirmation = {
                        openAlertDialog = false
                    }
                )
            }
        }
    }
}







