package com.headmostlab.findmovie2.di

import android.content.Context
import com.headmostlab.findmovie2.di.module.*
import com.headmostlab.findmovie2.mvp.presenter.CollectionPresenter
import com.headmostlab.findmovie2.mvp.presenter.CollectionsPresenter
import com.headmostlab.findmovie2.mvp.presenter.MainPresenter
import com.headmostlab.findmovie2.ui.activity.MainActivity
import com.headmostlab.findmovie2.ui.adapter.CollectionAdapter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CommonModule::class,
        ApiModule::class,
        CiceroneModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(collectionsPresenter: CollectionsPresenter)
    fun inject(collectionAdapter: CollectionAdapter)
    fun inject(collectionPresenter: CollectionPresenter)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder
        fun build(): AppComponent
    }
}