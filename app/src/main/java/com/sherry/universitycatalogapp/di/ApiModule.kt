package com.sherry.universitycatalogapp.di


import com.sherry.universitycatalogapp.data.remote.UniversityRemoteDataSource
import com.sherry.universitycatalogapp.data.remote.UniversityRemoteDataSourceImpl
import com.sherry.universitycatalogapp.data.repository.UniversityRepository
import com.sherry.universitycatalogapp.data.repository.UniversityRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ApiModule {
    @Binds
    abstract fun bindsRemoteDataSource(
        universityRemoteDataSourceImpl: UniversityRemoteDataSourceImpl
    ): UniversityRemoteDataSource

    @Binds
    abstract fun bindsToastRepository(
        universityRepositoryImpl: UniversityRepositoryImpl
    ): UniversityRepository
}