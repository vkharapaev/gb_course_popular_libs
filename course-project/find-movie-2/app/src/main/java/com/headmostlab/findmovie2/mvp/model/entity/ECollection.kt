package com.headmostlab.findmovie2.mvp.model.entity

import androidx.annotation.StringRes
import com.headmostlab.findmovie2.R

enum class ECollection(@StringRes val title: Int, val request: String) {
    NOW_PLAYING(R.string.movie_collection_now_playing, "now_playing"),
    UPCOMING(R.string.movie_collection_upcoming, "upcoming"),
    POPULAR(R.string.movie_collection_popular, "popular"),
    TOP_RATED(R.string.movie_collection_top_rated, "top_rated"),
}