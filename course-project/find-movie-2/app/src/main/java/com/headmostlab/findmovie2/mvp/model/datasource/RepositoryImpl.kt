package com.headmostlab.findmovie2.mvp.model.datasource

import com.headmostlab.findmovie2.mvp.model.datasource.local.database.DbDataSource
import com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb.TMDbDataSource
import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.model.entity.FullMovie
import com.headmostlab.findmovie2.mvp.model.entity.Person
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import com.headmostlab.findmovie2.mvp.model.repository.Repository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RepositoryImpl constructor(
    private val apiDataSource: TMDbDataSource,
    private val dbDataSource: DbDataSource
) : Repository {
    override fun getMovies(request: String, page: Int?): Single<List<ShortMovie>> =
        apiDataSource.getMovies(request, page)

    override fun getNowPlayingMovies(): Single<List<ShortMovie>> =
        apiDataSource.getNowPlayingMovies()

    override fun getUpcomingMovies(): Single<List<ShortMovie>> = apiDataSource.getUpcomingMovies()
    override fun getPopularMovies(): Single<List<ShortMovie>> = apiDataSource.getPopularMovies()
    override fun getMovie(movieId: Int): Single<FullMovie> = apiDataSource.getMovie(movieId)
    override fun getVideos(movieId: Int): Single<List<String>> = apiDataSource.getVideos(movieId)
    override fun getPeople(movieId: Int): Single<List<Person>> = apiDataSource.getPeople(movieId)
    override fun storeCollections(collections: List<Collection>): Completable =
        dbDataSource.storeCollections(collections)

    override fun getCollections(): Single<List<Collection>> = dbDataSource.getCollections()
    override fun getCollection(id: Int): Single<Collection> = dbDataSource.getCollection(id)
}