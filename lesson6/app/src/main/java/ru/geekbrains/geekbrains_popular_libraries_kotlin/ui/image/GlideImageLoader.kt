package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.image.IImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.image.IImageLoader
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import java.io.ByteArrayOutputStream

class GlideImageLoader(
    private val imageCache: IImageCache,
    private val networkStatus: INetworkStatus
) : IImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        networkStatus.isOnlineSingle().observeOn(AndroidSchedulers.mainThread())
            .subscribe({ isOnline ->
                if (isOnline) {
                    Glide.with(container.context)
                        .asBitmap()
                        .load(url)
                        .listener(requestListener(url))
                        .into(container)
                } else {
                    imageCache.getCachedImage(url).observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ image ->
                            Glide.with(container.context)
                                .asBitmap()
                                .load(image)
                                .into(container)
                        }, { })
                }
            }, {
                print("GlideImageLoader: error: ${it.printStackTrace()}")
            })
    }

    private fun requestListener(url: String) = object : RequestListener<Bitmap> {
        override fun onResourceReady(
            bitmap: Bitmap?,
            model: Any?,
            target: Target<Bitmap>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            //Save to cache
            val os = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, os)
            imageCache.saveImage(url, os.toByteArray()).subscribe()
            return false
        }

        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Bitmap>?,
            isFirstResource: Boolean
        ): Boolean {
            //Обработка провала загрузки
            return false
        }
    }

}