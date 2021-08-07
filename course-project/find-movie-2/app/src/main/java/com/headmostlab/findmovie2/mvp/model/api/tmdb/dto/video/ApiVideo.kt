package com.headmostlab.findmovie2.mvp.model.api.tmdb.dto.video

import com.google.gson.annotations.SerializedName

data class ApiVideo(
    @SerializedName("id") val id: String,
    @SerializedName("key") val key: String,
    @SerializedName("name") val name: String,
    @SerializedName("site") val site: String,
    @SerializedName("type") val type: String
)
