package ru.geekbrains.geekbrains_popular_libraries_kotlin.di

import dagger.Component
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.module.*
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.user.UserSubcomponent
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.MainPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        ImageModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {

    fun userSubcomponent(): UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}