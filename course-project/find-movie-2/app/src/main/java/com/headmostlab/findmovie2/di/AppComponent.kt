package com.headmostlab.findmovie2.di

import com.headmostlab.findmovie2.di.module.ApiModule
import com.headmostlab.findmovie2.di.module.CiceroneModule
import com.headmostlab.findmovie2.mvp.presenter.MainPresenter
import com.headmostlab.findmovie2.mvp.presenter.MovieCollectionsPresenter
import com.headmostlab.findmovie2.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        CiceroneModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(movieCollectionsPresenter: MovieCollectionsPresenter)
}