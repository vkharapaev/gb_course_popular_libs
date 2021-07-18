package com.headmostlab.gbconverter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.headmostlab.gbconverter.domain.Image
import com.headmostlab.gbconverter.domain.ImageConverter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.lang.RuntimeException

class AndroidImageConverter : ImageConverter {
    companion object {
        private const val IMAGE_QUALITY = 100
    }

    override fun convert(image: Image, saveToPath: File): Single<File> =
        Single.create<File> { emitter ->
            try {
                Thread.sleep(1000)
                val bmp = BitmapFactory.decodeByteArray(image.data, 0, image.data.size)
                bmp.compress(Bitmap.CompressFormat.PNG, IMAGE_QUALITY, saveToPath.outputStream())
                if (!emitter.isDisposed) {
                    emitter.onSuccess(saveToPath)
                }
            } catch (e: Throwable) {
                if (!emitter.isDisposed) {
                    emitter.onError(e)
                }
            }
        }.subscribeOn(Schedulers.computation())

}