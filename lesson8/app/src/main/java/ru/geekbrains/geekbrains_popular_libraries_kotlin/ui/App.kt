package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui

import android.app.Application
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.AppComponent
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.DaggerAppComponent
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.IRepositoryScopeContainer
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.IUserScopeContainer
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.module.AppModule
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.repository.RepositorySubcomponent
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.user.UserSubcomponent

class App : Application(), IUserScopeContainer, IRepositoryScopeContainer {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repositorySubcomponent: RepositorySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }
    
    fun initRepositorySubcomponent() = userSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }
    
    override fun releaseUserScope() {
        userSubcomponent = null
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }
}
