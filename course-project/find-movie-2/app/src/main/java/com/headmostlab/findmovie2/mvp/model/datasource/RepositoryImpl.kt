package com.headmostlab.findmovie2.mvp.model.datasource

import com.headmostlab.findmovie2.mvp.model.datasource.local.database.DataConverter
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.RoomDb
import com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb.TMDbDataSource
import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.model.entity.FullMovie
import com.headmostlab.findmovie2.mvp.model.entity.Person
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import com.headmostlab.findmovie2.mvp.model.repository.Repository
import io.reactivex.rxjava3.core.Single

class RepositoryImpl constructor(
    private val dataSource: TMDbDataSource,
    private val db: RoomDb
) : Repository {
    override fun getNowPlayingMovies(): Single<List<ShortMovie>> = dataSource.getNowPlayingMovies()
    override fun getUpcomingMovies(): Single<List<ShortMovie>> = dataSource.getUpcomingMovies()
    override fun getPopularMovies(): Single<List<ShortMovie>> = dataSource.getPopularMovies()
    override fun getMovie(movieId: Int): Single<FullMovie> = dataSource.getMovie(movieId)
    override fun getCollections(): Single<List<Collection>> =
        db.collectionDao().getAll().map { it -> it.map { DataConverter.map(it) } }

    override fun getCollection(id: Int): Single<Collection> =
        db.collectionDao().get(id).map { DataConverter.map(it) }

    override fun getVideos(movieId: Int): Single<List<String>> = dataSource.getVideos(movieId)
    override fun getPeople(movieId: Int): Single<List<Person>> = dataSource.getPeople(movieId)
}