package com.headmostlab.findmovie2.mvp.model.api.tmdb.dto.movie

import com.google.gson.annotations.SerializedName

data class ApiMovies(
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("results") val results: List<ApiShortMovie>,
)
