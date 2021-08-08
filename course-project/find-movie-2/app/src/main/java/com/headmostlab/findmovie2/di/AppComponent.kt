package com.headmostlab.findmovie2.di

import android.content.Context
import com.headmostlab.findmovie2.di.module.*
import com.headmostlab.findmovie2.di.moviecollections.MovieCollectionsSubcomponent
import com.headmostlab.findmovie2.mvp.presenter.MainPresenter
import com.headmostlab.findmovie2.mvp.presenter.MovieCollectionsPresenter
import com.headmostlab.findmovie2.ui.activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        CiceroneModule::class,
        DataSourceModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun movieCollectionsSubcomponent(): MovieCollectionsSubcomponent
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder
        fun build(): AppComponent
    }
}