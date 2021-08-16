package com.headmostlab.findmovie2.ui.resource

import android.content.Context
import com.headmostlab.findmovie2.R
import com.headmostlab.findmovie2.mvp.model.entity.ECollection
import com.headmostlab.findmovie2.mvp.model.resource.ResourceManager

class AndroidResourceManager(private val context: Context) : ResourceManager {

    override fun getString(collection: ECollection): String =
        context.getString(
            when (collection) {
                ECollection.NOW_PLAYING -> R.string.movie_collection_now_playing
                ECollection.UPCOMING -> R.string.movie_collection_upcoming
                ECollection.POPULAR -> R.string.movie_collection_popular
                ECollection.TOP_RATED -> R.string.movie_collection_top_rated
            }
        )

}