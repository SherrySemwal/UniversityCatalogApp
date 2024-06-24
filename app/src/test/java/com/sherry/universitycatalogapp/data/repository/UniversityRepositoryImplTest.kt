package com.sherry.universitycatalogapp.data.repository

import com.sherry.universitycatalogapp.CoroutinesDispatcherTestRule
import com.sherry.universitycatalogapp.data.ApiResult
import com.sherry.universitycatalogapp.data.model.University
import com.sherry.universitycatalogapp.data.model.UniversityResponse
import com.sherry.universitycatalogapp.data.remote.UniversityRemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ToastRepositoryImplTest {

    @get:Rule
    val coroutinesRule = CoroutinesDispatcherTestRule()

    private val dispatcher: TestDispatcher
        get() = coroutinesRule.dispatcher

    private lateinit var universityRepositoryImpl: UniversityRepositoryImpl

    @RelaxedMockK
    lateinit var universityRemoteDataSource: UniversityRemoteDataSource

    private val webPages = listOf("http://www.augustana.de/")
    private val domains = listOf("augustana.de/")
    private val universityResponse = UniversityResponse(
        name = "Augustana Hochschule Neuendettelsau",
        country = "Germany",
        alphaTwoCode = "DE",
        webPages = webPages,
        domains = domains,
        stateProvince = ""
    )

    private val expectedUniversity = University(
        name = "Augustana Hochschule Neuendettelsau",
        country = "Germany",
        webpageUrl = "http://www.augustana.de/"
    )

    private val universityResponseList: List<UniversityResponse> = listOf(universityResponse)

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        universityRepositoryImpl = UniversityRepositoryImpl(universityRemoteDataSource, dispatcher)
    }

    @Test
    fun `getAllUniversity is called only once after calling getUniversityData`() = runTest {
        coEvery { universityRemoteDataSource.getUniversityData() } returns universityResponseList

        universityRepositoryImpl.getAllUniversity()

        coVerify(exactly = 1) { universityRemoteDataSource.getUniversityData() }
    }

    @Test
    fun `webPageUrl should be in correct format in the response`() = runTest {
        coEvery { universityRemoteDataSource.getUniversityData() } returns universityResponseList

        when (val universityApiResult = universityRepositoryImpl.getAllUniversity()) {
            is ApiResult.OnSuccess -> {
                Assert.assertEquals(expectedUniversity.webpageUrl, universityApiResult.data?.first()?.webpageUrl)
            }

            else -> ApiResult.OnFailure("")
        }
    }

}