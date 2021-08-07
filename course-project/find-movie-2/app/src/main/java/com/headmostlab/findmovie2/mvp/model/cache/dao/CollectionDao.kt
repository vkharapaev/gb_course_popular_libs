package com.headmostlab.findmovie2.mvp.model.cache.dao

import androidx.room.*
import com.headmostlab.findmovie2.mvp.model.cache.entities.Collection
import io.reactivex.rxjava3.core.Single

@Dao
interface CollectionDao {
    @Query("SELECT * FROM Collection")
    fun getAll(): Single<List<Collection>>

    @Query("SELECT * FROM Collection WHERE request = :rq")
    fun getByRequest(rq: String): Collection

    @Query("SELECT * FROM Collection WHERE id = :id")
    fun get(id: Int): Single<Collection>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(collections: List<Collection>)

    @Delete
    fun delete(collection: Collection)
}