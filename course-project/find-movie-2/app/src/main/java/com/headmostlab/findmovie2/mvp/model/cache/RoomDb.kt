package com.headmostlab.findmovie2.mvp.model.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.headmostlab.findmovie2.mvp.model.cache.dao.CollectionDao
import com.headmostlab.findmovie.data.datasource.local.dao.CollectionMovieCrossRefDao
import com.headmostlab.findmovie2.mvp.model.cache.dao.MovieDao
import com.headmostlab.findmovie2.mvp.model.cache.dao.RemoteKeyDao
import com.headmostlab.findmovie2.mvp.model.cache.entities.Collection
import com.headmostlab.findmovie2.mvp.model.cache.entities.CollectionMovieCrossRef
import com.headmostlab.findmovie2.mvp.model.cache.entities.Movie
import com.headmostlab.findmovie2.mvp.model.cache.entities.RemoteKey

@Database(
    entities = [Collection::class, Movie::class, CollectionMovieCrossRef::class, RemoteKey::class],
    version = 1,
    exportSchema = true
)
abstract class RoomDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun collectionDao(): CollectionDao
    abstract fun collectionMovieCrossRefDao(): CollectionMovieCrossRefDao
}
