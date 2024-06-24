package com.sherry.universitycatalogapp.ui.university

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sherry.universitycatalogapp.data.ApiResult
import com.sherry.universitycatalogapp.data.repository.UniversityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(private val repository: UniversityRepository) : ViewModel() {

    private val _toastUiState = MutableStateFlow<UniversityUiState>(UniversityUiState.Loading)
    val toastUiState: StateFlow<UniversityUiState> = _toastUiState.asStateFlow()

    init {
        getUniversities()
    }

    private fun getUniversities() = viewModelScope.launch {
        when (val result = repository.getAllUniversity()) {
            ApiResult.NetworkError -> {
                _toastUiState.value = UniversityUiState.Error(null)
            }

            is ApiResult.OnFailure -> {
                _toastUiState.value = UniversityUiState.Error(result.error ?: "")
            }

            is ApiResult.OnSuccess -> {
                _toastUiState.value = UniversityUiState.Success(result.data ?: emptyList())
            }
        }
    }
}