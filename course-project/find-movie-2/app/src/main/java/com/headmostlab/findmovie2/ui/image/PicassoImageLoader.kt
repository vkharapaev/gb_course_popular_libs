package com.headmostlab.findmovie2.ui.image

import android.widget.ImageView
import com.headmostlab.findmovie2.mvp.model.image.ImageLoader
import com.squareup.picasso.Picasso

class PicassoImageLoader : ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Picasso.get().load(url).into(container)
    }
}