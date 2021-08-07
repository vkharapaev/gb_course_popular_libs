package com.headmostlab.findmovie2.mvp.model.api.tmdb.dto.video

import com.google.gson.annotations.SerializedName
import com.headmostlab.findmovie2.mvp.model.api.tmdb.dto.video.ApiVideo

data class ApiVideos(
    @SerializedName("id") val movieId: Int,
    @SerializedName("results") val videos: List<ApiVideo>
)
