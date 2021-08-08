package com.headmostlab.findmovie2.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule {

    @Provides
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}