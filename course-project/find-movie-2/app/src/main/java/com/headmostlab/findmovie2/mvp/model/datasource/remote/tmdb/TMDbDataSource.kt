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
        service.getMovies(request, apiKey, page).map { DataConverter.map(it) }

    fun getMovie(id: Int): Single<FullMovie> =
        service.getMovie(id, apiKey).map { DataConverter.map(it) }

    fun getVideos(movieId: Int): Single<List<String>> =
        service.getVideos(movieId, apiKey).map { DataConverter.map(it) }

    fun getPeople(movieId: Int): Single<List<Person>> =
        service.getCredits(movieId, apiKey).map { DataConverter.map(it) }
}