package com.headmostlab.findmovie2.di.moviecollections

import com.headmostlab.findmovie2.di.moviecollections.module.MovieCollectionsModule
import com.headmostlab.findmovie2.mvp.presenter.MovieCollectionsPresenter
import dagger.Subcomponent

@MovieCollectionsScope
@Subcomponent(
    modules = [
        MovieCollectionsModule::class
    ]
)
interface MovieCollectionsSubcomponent {
    fun inject(movieCollectionsPresenter: MovieCollectionsPresenter)
}