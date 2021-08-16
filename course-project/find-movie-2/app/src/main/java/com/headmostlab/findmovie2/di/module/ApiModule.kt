package com.headmostlab.findmovie2.di.module

import android.content.Context
import com.headmostlab.findmovie2.BuildConfig
import com.headmostlab.findmovie2.di.qualifier.QualApiKey
import com.headmostlab.findmovie2.di.qualifier.QualBaseUrl
import com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb.TMDbApi
import com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb.TMDbApiService
import com.headmostlab.findmovie2.mvp.model.network.NetworkStatus
import com.headmostlab.findmovie2.ui.network.AndroidNetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @QualBaseUrl
    @Provides
    fun baseUrl(): String = "https://api.tmdb.org/"

    @QualApiKey
    @Provides
    fun apiKey(): String = BuildConfig.TMDB_API_KEY

    @Singleton
    @Provides
    fun provideApi(@QualBaseUrl baseUrl: String): TMDbApiService =
        TMDbApi(baseUrl).getService()
}