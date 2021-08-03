package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class CommonModule {
    @Provides
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}