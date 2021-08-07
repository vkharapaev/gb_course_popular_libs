package com.headmostlab.findmovie2.mvp.model.api.tmdb

import javax.inject.Inject

class TMDbHostProvider @Inject constructor() : HostProvider {
    override fun getHostUrl() = "https://api.tmdb.org/"
}