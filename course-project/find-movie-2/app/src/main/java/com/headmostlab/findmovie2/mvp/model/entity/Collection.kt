package com.headmostlab.findmovie2.mvp.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collection(
    val id: Int,
    val eCollection: ECollection,
    val request: String,
) : Parcelable