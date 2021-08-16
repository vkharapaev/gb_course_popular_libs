package com.headmostlab.findmovie2.mvp.model.datasource

import com.headmostlab.findmovie2.mvp.model.datasource.local.database.DbDataSource
import com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb.TMDbDataSource
import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.model.entity.FullMovie
import com.headmostlab.findmovie2.mvp.model.entity.Person
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import com.headmostlab.findmovie2.mvp.model.network.NetworkStatus
import com.headmostlab.findmovie2.mvp.model.repository.Repository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryImpl constructor(
    private val apiDataSource: TMDbDataSource,
    private val dbDataSource: DbDataSource,
    private val networkStatus: NetworkStatus
) : Repository {

    override fun storeCollections(collections: List<Collection>): Completable {
        return dbDataSource.storeCollections(collections).subscribeOn(Schedulers.io())
    }

    override fun getCollections(): Single<List<Collection>> {
        return dbDataSource.getCollections().subscribeOn(Schedulers.io())
    }

    override fun getCollection(id: Int): Single<Collection> {
        return dbDataSource.getCollection(id).subscribeOn(Schedulers.io())
    }

    override fun getMovies(collection: Collection, page: Int): Single<List<ShortMovie>> {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                apiDataSource.getMovies(collection.request, page).flatMap { movies ->
                    dbDataSource.storeMovies(collection.request, movies).toSingleDefault(movies)
                }
            } else {
                dbDataSource.getMovies(collection)
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun getMovie(movieId: Int): Single<FullMovie> {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                apiDataSource.getMovie(movieId).flatMap { movie ->
                    dbDataSource.storeFullMovie(movie).toSingleDefault(movie)
                }
            } else {
                dbDataSource.getFullMovie(movieId)
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun getVideos(movieId: Int): Single<List<String>> {
        return apiDataSource.getVideos(movieId).subscribeOn(Schedulers.io())
    }

    override fun getPeople(movieId: Int): Single<List<Person>> {
        return apiDataSource.getPeople(movieId).subscribeOn(Schedulers.io())
    }
}