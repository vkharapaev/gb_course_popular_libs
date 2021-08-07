package com.headmostlab.findmovie2.mvp.model.api.tmdb.dto.credit

import com.google.gson.annotations.SerializedName

data class ApiCredits(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val cast: List<ApiCast>,
    @SerializedName("crew") val crew: List<ApiCrew>
)
