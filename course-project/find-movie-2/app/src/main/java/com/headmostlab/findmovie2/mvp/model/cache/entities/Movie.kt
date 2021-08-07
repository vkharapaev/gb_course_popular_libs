package com.headmostlab.findmovie2.mvp.model.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val date: String,
    val rating: Double,
    val popularity: Double,
    val poster: String?,
    val backdrop: String?
)
