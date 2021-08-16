package com.headmostlab.findmovie2.ui

import android.app.Application
import com.headmostlab.findmovie2.di.AppComponent
import com.headmostlab.findmovie2.di.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder().appContext(this).build()
    }
}