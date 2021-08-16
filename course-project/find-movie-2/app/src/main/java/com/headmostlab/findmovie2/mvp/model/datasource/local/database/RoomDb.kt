package com.headmostlab.findmovie2.mvp.model.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.dao.CollectionDao
import com.headmostlab.findmovie.data.datasource.local.dao.CollectionMovieCrossRefDao
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.dao.MovieDao
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.dao.FullMovieDao
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.dao.RemoteKeyDao
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.entities.*
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.entities.Collection

@Database(
    entities = [Collection::class, Movie::class, FullMovie::class, CollectionMovieCrossRef::class, RemoteKey::class],
    version = 1,
    exportSchema = true
)
abstract class RoomDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun fullMovieDao(): FullMovieDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun collectionDao(): CollectionDao
    abstract fun collectionMovieCrossRefDao(): CollectionMovieCrossRefDao
}
