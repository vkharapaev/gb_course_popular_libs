package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.repository

import dagger.Subcomponent
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.repository.module.RepositoryModule
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.RepositoryPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.UserPresenter

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositorySubcomponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}