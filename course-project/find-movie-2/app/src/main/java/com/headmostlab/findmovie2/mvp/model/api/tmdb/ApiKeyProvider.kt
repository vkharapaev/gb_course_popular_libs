package com.headmostlab.findmovie2.mvp.model.api.tmdb

interface ApiKeyProvider {
    fun getApiKey(): String
}