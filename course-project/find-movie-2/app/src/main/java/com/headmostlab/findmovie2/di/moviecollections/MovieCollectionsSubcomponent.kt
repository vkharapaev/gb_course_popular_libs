package com.headmostlab.findmovie2.di.moviecollections

import com.headmostlab.findmovie2.di.moviecollections.module.MovieCollectionsModule
import com.headmostlab.findmovie2.mvp.presenter.CollectionsPresenter
import dagger.Subcomponent

@MovieCollectionsScope
@Subcomponent(
    modules = [
        MovieCollectionsModule::class
    ]
)
interface MovieCollectionsSubcomponent {
    fun inject(collectionsPresenter: CollectionsPresenter)
}