package com.sherry.universitycatalogapp.ui.university

import com.sherry.universitycatalogapp.data.model.University

sealed interface UniversityUiState {
    data object Loading : UniversityUiState
    data class Success(val universities: List<University>) : UniversityUiState
    data class Error(val error: String?) : UniversityUiState
}