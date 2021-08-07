package com.headmostlab.findmovie2.mvp.model.entity

data class ShortMovie(
    val id: Int,
    val title: String,
    val date: String,
    val rating: Double,
    val popularity: Double,
    val poster: String?,
    val backdrop: String?
)