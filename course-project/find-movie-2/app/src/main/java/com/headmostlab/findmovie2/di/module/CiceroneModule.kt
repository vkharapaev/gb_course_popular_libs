package com.headmostlab.findmovie2.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.headmostlab.findmovie2.mvp.model.navigator.IScreens
import com.headmostlab.findmovie2.ui.navigation.AndroidScreens
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

    @Singleton
    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Singleton
    @Provides
    fun provideScreens(): IScreens = AndroidScreens()

}