package com.headmostlab.findmovie2.di.module

import com.headmostlab.findmovie2.BuildConfig
import com.headmostlab.findmovie2.mvp.model.api.tmdb.TMDbApi
import com.headmostlab.findmovie2.mvp.model.api.tmdb.TMDbApiService
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://api.tmdb.org/"

    @Named("apiKey")
    fun apiKey(): String = BuildConfig.TMDB_API_KEY

    @Singleton
    @Provides
    fun provideApi(@Named("baseUrl") baseUrl: String): TMDbApiService =
        TMDbApi(baseUrl).getService()
}