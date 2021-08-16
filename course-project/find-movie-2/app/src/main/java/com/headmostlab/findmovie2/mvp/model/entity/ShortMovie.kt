package com.headmostlab.findmovie2.mvp.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShortMovie(
    val id: Int,
    val title: String,
    val date: String,
    val rating: Double,
    val popularity: Double,
    val poster: String?,
    val backdrop: String?
) : Parcelable