package com.headmostlab.findmovie2.mvp.model.datasource

import com.headmostlab.findmovie2.mvp.model.datasource.local.sharedpreferences.SharedPreferencesDataSource
import com.headmostlab.findmovie2.mvp.model.repository.SharedPreferencesRepository

class SharedPreferencesRepositoryImpl(private val sharedPrefDataSource: SharedPreferencesDataSource) :
    SharedPreferencesRepository {
    override fun isAppFirstStart(): Boolean = sharedPrefDataSource.isAppFirstStart()
    override fun recordAppFirstStart() = sharedPrefDataSource.recordAppFirstStart()
}