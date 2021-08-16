package com.headmostlab.findmovie2.mvp.model.datasource.local.sharedpreferences

import android.content.Context

class SharedPreferencesDataSource(context: Context) {

    companion object {
        private const val APP_SHARED_PREFERENCES = "app-shared-preferences"
        private const val ID_APP_FIRST_START = "APP_FIRST_START"
    }

    private val sharedPref =
        context.getSharedPreferences(APP_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    fun isAppFirstStart(): Boolean = sharedPref.getBoolean(ID_APP_FIRST_START, true)

    fun recordAppFirstStart() = sharedPref.edit().putBoolean(ID_APP_FIRST_START, false).apply()
}