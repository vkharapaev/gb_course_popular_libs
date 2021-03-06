package com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb

import com.headmostlab.findmovie2.mvp.model.image.ImageHostProvider

class TMDbImageHostProvider : ImageHostProvider {
    override fun getHostUrl() = "https://image.tmdb.org/t/p/w300"
    override fun getProfileUrl() = "https://image.tmdb.org/t/p/w185"
}