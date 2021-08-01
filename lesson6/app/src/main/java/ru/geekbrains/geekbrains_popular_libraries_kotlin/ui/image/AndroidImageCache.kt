package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.image

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.CachedImage
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao.ImageDao
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.image.IImageCache
import java.io.File
import java.io.FileOutputStream

class AndroidImageCache(private val imageDao: ImageDao, private val cacheDir: File) : IImageCache {

    override fun saveImage(url: String, bytes: ByteArray): Completable =
        Completable.fromAction {
            val fileName = url.hashCode().toString() + ".image"
            val filePath = File(cacheDir, fileName)
            writeFile(filePath, bytes)
            imageDao.insert(CachedImage(url, filePath.absolutePath))
        }.subscribeOn(Schedulers.io())

    override fun getCachedImage(url: String): Single<ByteArray?> =
        imageDao.select(url)
            .subscribeOn(Schedulers.io())
            .flatMap {
                val cachedImage = it ?: return@flatMap Single.just(null)
                return@flatMap Single.just(readFile(cachedImage.localPath))
            }

    private fun readFile(filePath: String): ByteArray? {
        val file = File(filePath)
        if (!file.exists()) return null
        return file.readBytes()
    }

    private fun writeFile(filePath: File, bytes: ByteArray) {
        if (filePath.exists()) filePath.delete()
        FileOutputStream(filePath).apply {
            write(bytes)
            close()
        }
    }

}
