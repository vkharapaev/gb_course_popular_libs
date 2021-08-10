package com.headmostlab.findmovie2.di.module

import android.widget.ImageView
import com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb.TMDbImageHostProvider
import com.headmostlab.findmovie2.mvp.model.image.ImageHostProvider
import com.headmostlab.findmovie2.mvp.model.image.ImageLoader
import com.headmostlab.findmovie2.ui.image.PicassoImageLoader
import dagger.Module
import dagger.Provides

@Module
class ImageModule {

    @Provides
    fun provideImageModule(): ImageLoader<ImageView> = PicassoImageLoader()

    @Provides
    fun provideImageHostProvider(): ImageHostProvider = TMDbImageHostProvider()
}