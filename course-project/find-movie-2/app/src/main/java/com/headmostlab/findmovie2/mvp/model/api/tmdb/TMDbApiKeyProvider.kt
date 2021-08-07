package com.headmostlab.findmovie2.mvp.model.api.tmdb

import com.headmostlab.findmovie2.BuildConfig
import javax.inject.Inject

class TMDbApiKeyProvider @Inject constructor() : ApiKeyProvider {
    override fun getApiKey() = BuildConfig.TMDB_API_KEY
}