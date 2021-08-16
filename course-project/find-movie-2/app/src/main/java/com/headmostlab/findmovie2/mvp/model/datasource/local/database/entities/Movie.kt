package com.headmostlab.findmovie2.mvp.model.datasource.local.database.entities

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

@Entity
class FullMovie(
    @PrimaryKey val id: Int,
    val title: String,
    val origTitle: String,
    val duration: Int,
    val rating: Double,
    val votesAverage: Double,
    val votesCount: Int,
    val budget: Int,
    val revenue: Long,
    val date: String,
    val description: String,
    val poster: String,
)
