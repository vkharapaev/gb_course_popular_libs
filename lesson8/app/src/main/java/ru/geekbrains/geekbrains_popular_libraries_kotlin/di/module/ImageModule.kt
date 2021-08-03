package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.module

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.room.RoomImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.image.IImageLoader
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.image.GlideImageLoader
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageModule {

    companion object {
        const val IMAGE_CACHE_DIR = "IMAGE_CACHE_DIR"
    }

    @Named(IMAGE_CACHE_DIR)
    @Provides
    fun provideImageCacheDir(app: App): File = app.cacheDir

    @Provides
    fun provideImageCache(database: Database, @Named(IMAGE_CACHE_DIR) path: File): IImageCache =
        RoomImageCache(database.imageDao, path)

    @Singleton
    @Provides
    fun provideImageLoader(cache: IImageCache, network: INetworkStatus): IImageLoader<ImageView> =
        GlideImageLoader(cache, network)
}