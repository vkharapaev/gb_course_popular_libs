package com.headmostlab.findmovie2.mvp.model.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.entities.FullMovie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface FullMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: FullMovie): Completable

    @Query("SELECT * FROM FullMovie WHERE id = :id")
    fun get(id: Int): Single<FullMovie>
}