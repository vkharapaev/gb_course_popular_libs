package com.headmostlab.findmovie2.di.moviecollections

import com.headmostlab.findmovie2.di.moviecollections.module.MovieCollectionsModule
import com.headmostlab.findmovie2.mvp.presenter.CollectionsPresenter
import com.headmostlab.findmovie2.ui.adapter.CollectionAdapter
import dagger.Subcomponent

@MovieCollectionsScope
@Subcomponent(
    modules = [
        MovieCollectionsModule::class
    ]
)
interface CollectionsSubcomponent {
    fun inject(collectionsPresenter: CollectionsPresenter)
    fun inject(collection: CollectionAdapter)
}