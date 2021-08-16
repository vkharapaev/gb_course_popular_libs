package com.headmostlab.findmovie2.mvp.model.datasource.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.entities.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<Movie>)

    @Query("DELETE FROM Movie")
    fun deleteAll()

    @Query("SELECT * FROM Movie")
    fun getAllMovies(): Single<List<Movie>>

    @Query(
        """SELECT m.* from CollectionMovieCrossRef ref INNER JOIN Movie m on ref.movieId = m.id 
        WHERE ref.collectionId = :collectionId ORDER BY ref.page ASC, m.popularity DESC limit :maxCount"""
    )
    fun pagingSource(collectionId: Int, maxCount: Int = 1_000_000_000): PagingSource<Int, Movie>

    @Query(
        """SELECT m.* from CollectionMovieCrossRef ref INNER JOIN Movie m on ref.movieId = m.id 
        WHERE ref.collectionId = :collectionId AND ref.page = :page ORDER BY ref.page ASC, m.popularity DESC"""
    )
    fun getMovies(collectionId: Int, page: Int = 0): Single<List<Movie>>
}
