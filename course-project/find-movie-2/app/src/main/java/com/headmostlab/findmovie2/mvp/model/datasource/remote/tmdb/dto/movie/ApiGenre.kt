package com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb.dto.movie

import com.google.gson.annotations.SerializedName

data class ApiGenre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)