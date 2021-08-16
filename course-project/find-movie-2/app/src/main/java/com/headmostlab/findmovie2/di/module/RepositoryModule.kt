package com.headmostlab.findmovie2.di.module

import com.headmostlab.findmovie2.mvp.model.datasource.RepositoryImpl
import com.headmostlab.findmovie2.mvp.model.datasource.SharedPreferencesRepositoryImpl
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.DbDataSource
import com.headmostlab.findmovie2.mvp.model.datasource.local.sharedpreferences.SharedPreferencesDataSource
import com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb.TMDbDataSource
import com.headmostlab.findmovie2.mvp.model.network.NetworkStatus
import com.headmostlab.findmovie2.mvp.model.repository.Repository
import com.headmostlab.findmovie2.mvp.model.repository.SharedPreferencesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun provideSharedPreferencesRepository(sharedPrefDataSource: SharedPreferencesDataSource): SharedPreferencesRepository =
        SharedPreferencesRepositoryImpl(sharedPrefDataSource)

    @Singleton
    @Provides
    fun provideRepository(
        db: DbDataSource,
        api: TMDbDataSource,
        networkStatus: NetworkStatus
    ): Repository = RepositoryImpl(api, db, networkStatus)
}