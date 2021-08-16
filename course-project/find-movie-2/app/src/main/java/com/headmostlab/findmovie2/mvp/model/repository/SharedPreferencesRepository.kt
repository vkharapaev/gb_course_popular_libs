package com.headmostlab.findmovie2.mvp.model.repository

interface SharedPreferencesRepository {
    fun isAppFirstStart(): Boolean
    fun recordAppFirstStart()
}