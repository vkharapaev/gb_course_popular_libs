package com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb.dto.video

import com.google.gson.annotations.SerializedName

data class ApiVideos(
    @SerializedName("id") val movieId: Int,
    @SerializedName("results") val videos: List<ApiVideo>
)
