package com.headmostlab.findmovie2.di

import android.content.Context
import com.headmostlab.findmovie2.di.module.*
import com.headmostlab.findmovie2.di.moviecollections.CollectionsSubcomponent
import com.headmostlab.findmovie2.mvp.presenter.MainPresenter
import com.headmostlab.findmovie2.ui.activity.MainActivity
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
    fun movieCollectionsSubcomponent(): CollectionsSubcomponent
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder
        fun build(): AppComponent
    }
}