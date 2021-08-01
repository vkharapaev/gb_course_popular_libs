package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CachedImage(
    @PrimaryKey
    val url: String,
    val localPath: String
)