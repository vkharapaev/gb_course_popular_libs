package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.navigation.AndroidScreens

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    val screens = AndroidScreens()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}