package com.headmostlab.findmovie2.di.module

import android.content.Context
import androidx.room.Room
import com.headmostlab.findmovie2.di.qualifier.QualApiKey
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.DbDataSource
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.RoomDb
import com.headmostlab.findmovie2.mvp.model.datasource.local.sharedpreferences.SharedPreferencesDataSource
import com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb.TMDbApiService
import com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb.TMDbDataSource
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun provideDbDataSource(context: Context): DbDataSource {
        val db = Room.databaseBuilder(context, RoomDb::class.java, "moviedb")
            .fallbackToDestructiveMigration()
            .build()

        return DbDataSource(db)
    }

    @Provides
    fun provideApiDataSource(api: TMDbApiService, @QualApiKey apiKey: String) =
        TMDbDataSource(api, apiKey)

    @Provides
    fun provideSharedPreferencesDataSource(context: Context) = SharedPreferencesDataSource(context)
}