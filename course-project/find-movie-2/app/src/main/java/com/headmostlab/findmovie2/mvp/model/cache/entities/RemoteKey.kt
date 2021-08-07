package com.headmostlab.findmovie2.mvp.model.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKey(
    @PrimaryKey
    val label: String,
    val nextKey: Int?
)
