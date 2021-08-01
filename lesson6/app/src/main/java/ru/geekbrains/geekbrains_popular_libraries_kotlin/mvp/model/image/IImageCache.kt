package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.image

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IImageCache {
    fun saveImage(url: String, bytes: ByteArray): Completable
    fun getCachedImage(url: String): Single<ByteArray?>
}