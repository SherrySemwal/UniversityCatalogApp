package com.sherry.universitycatalogapp.data.remote

import com.sherry.universitycatalogapp.data.model.UniversityResponse
import com.sherry.universitycatalogapp.data.service.ApiInterface
import com.sherry.universitycatalogapp.utils.getResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UniversityRemoteDataSourceImplTest {
    private lateinit var universityRemoteDataSourceImpl: UniversityRemoteDataSourceImpl

    @RelaxedMockK
    lateinit var apiInterface: ApiInterface

    private val universityResponseList: Response<List<UniversityResponse>> = Response.success(listOf())

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        universityRemoteDataSourceImpl = UniversityRemoteDataSourceImpl(apiInterface)
    }

    @Test
    fun `getAllUniversity returns correct response`() = runTest {
        coEvery { apiInterface.getAllUniversity() } returns universityResponseList

        val toastList = universityRemoteDataSourceImpl.getUniversityData()

        Assert.assertEquals(universityResponseList.getResponse(), toastList)
    }

    @Test
    fun `getAllUniversity is called only once after calling getToastData`() = runTest {
        coEvery { apiInterface.getAllUniversity() } returns universityResponseList

        universityRemoteDataSourceImpl.getUniversityData()

        coVerify(exactly = 1) { apiInterface.getAllUniversity() }
    }
}