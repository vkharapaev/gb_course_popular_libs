package com.headmostlab.findmovie2.mvp.model.datasource.remote.tmdb

import com.headmostlab.findmovie2.mvp.model.entity.FullMovie
import com.headmostlab.findmovie2.mvp.model.entity.Person
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class TMDbDataSource constructor(
    private val service: TMDbApiService,
    @Named("apiKey") private val apiKey: String
) {
    fun getMovies(request: String, page: Int? = null): Single<List<ShortMovie>> =
        service.getMovies(request, apiKey, page).subscribeOn(Schedulers.io())
            .map { DataConverter.map(it) }

    fun getNowPlayingMovies(): Single<List<ShortMovie>> =
        service.getNowPlayingMovies(apiKey).subscribeOn(Schedulers.io())
            .map { DataConverter.map(it) }

    fun getUpcomingMovies(): Single<List<ShortMovie>> =
        service.getUpcomingMovies(apiKey).subscribeOn(Schedulers.io()).map { DataConverter.map(it) }

    fun getPopularMovies(): Single<List<ShortMovie>> =
        service.getPopularMovies(apiKey).subscribeOn(Schedulers.io()).map { DataConverter.map(it) }

    fun getMovie(id: Int): Single<FullMovie> =
        service.getMovie(id, apiKey).subscribeOn(Schedulers.io()).map { DataConverter.map(it) }

    fun getVideos(movieId: Int): Single<List<String>> =
        service.getVideos(movieId, apiKey).subscribeOn(Schedulers.io())
            .map { DataConverter.map(it) }

    fun getPeople(movieId: Int): Single<List<Person>> =
        service.getCredits(movieId, apiKey).subscribeOn(Schedulers.io())
            .map { DataConverter.map(it) }
}