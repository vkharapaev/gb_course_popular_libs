package com.headmostlab.findmovie2.di.module

import android.content.Context
import com.headmostlab.findmovie2.mvp.model.network.NetworkStatus
import com.headmostlab.findmovie2.mvp.model.resource.ResourceManager
import com.headmostlab.findmovie2.ui.network.AndroidNetworkStatus
import com.headmostlab.findmovie2.ui.resource.AndroidResourceManager
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Module
class CommonModule {

    @Provides
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun provideResourceManage(context: Context): ResourceManager = AndroidResourceManager(context)

    @Singleton
    @Provides
    fun provideNetworkStatus(context: Context): NetworkStatus = AndroidNetworkStatus(context)
}