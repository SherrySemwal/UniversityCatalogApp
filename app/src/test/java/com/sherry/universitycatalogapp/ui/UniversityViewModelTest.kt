package com.sherry.universitycatalogapp.ui

import com.sherry.universitycatalogapp.CoroutinesDispatcherTestRule
import com.sherry.universitycatalogapp.data.ApiResult
import com.sherry.universitycatalogapp.data.repository.UniversityRepository
import com.sherry.universitycatalogapp.ui.university.UniversityUiState
import com.sherry.universitycatalogapp.ui.university.UniversityViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UniversityViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutinesDispatcherTestRule()

    private val universityRepository: UniversityRepository = mockk()
    private lateinit var universityViewModel: UniversityViewModel

    @Test
    fun `getUniversities should return on success`() = runTest {
        coEvery { universityRepository.getAllUniversity() } returns ApiResult.OnSuccess(emptyList())
        universityViewModel = UniversityViewModel(universityRepository)

        Assert.assertEquals(
            UniversityUiState.Success(emptyList()),
            universityViewModel.toastUiState.value
        )
    }

    @Test
    fun `getUniversities should return error`() = runTest {
        coEvery { universityRepository.getAllUniversity() } returns ApiResult.OnFailure("Error")

        universityViewModel = UniversityViewModel(universityRepository)

        Assert.assertEquals(
            UniversityUiState.Error("Error"),
            universityViewModel.toastUiState.value
        )
    }

    @Test
    fun `getUniversities should return network error`() = runTest {
        coEvery { universityRepository.getAllUniversity() } returns ApiResult.NetworkError
        universityViewModel = UniversityViewModel(universityRepository)

        Assert.assertEquals(
            UniversityUiState.Error(null),
            universityViewModel.toastUiState.value
        )
    }
}