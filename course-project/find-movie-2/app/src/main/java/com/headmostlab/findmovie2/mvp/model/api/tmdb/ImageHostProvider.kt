package com.headmostlab.findmovie2.mvp.model.api.tmdb

interface ImageHostProvider {
    fun getHostUrl(): String
    fun getProfileUrl(): String
}
