package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomCachedImage
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.security.MessageDigest

class RoomImageCache(val db: Database, val dir: File) : IImageCache {
    private fun String.md5() = hash("MD5")
    private fun String.hash(algorithm: String) = MessageDigest.getInstance(algorithm).digest(toByteArray()).fold("", { str, it -> "%02x".format(it) })

    override fun getBytes(url: String) = Maybe.fromCallable {
        db.imageDao.findByUrl(url)?.let {
            File(it.localPath).inputStream().readBytes()
        }
    }.subscribeOn(Schedulers.io())

    override fun saveImage(url: String, bytes: ByteArray) = Completable.create { emitter ->
        if (!dir.exists() && !dir.mkdir()) {
            emitter.onError(IOException("Failed to create cache dir"))
            return@create
        }

        val fileFormat = if (url.contains(".jpg")) ".jpg" else ".png"
        val imageFile = File(dir, url.md5() + fileFormat)
        try {
            FileOutputStream(imageFile).use {
                it.write(bytes)
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
        db.imageDao.insert(RoomCachedImage(url, imageFile.path))
        emitter.onComplete()
    }.subscribeOn(Schedulers.io())

    override fun contains(url: String) = Single.fromCallable { db.imageDao.findByUrl(url) != null }.subscribeOn(Schedulers.io())

    override fun clear() = Completable.fromAction {
        db.imageDao.clear()
        dir.deleteRecursively()
    }.subscribeOn(Schedulers.io())



}