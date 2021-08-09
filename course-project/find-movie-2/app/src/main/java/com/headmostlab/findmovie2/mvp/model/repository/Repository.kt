package com.headmostlab.findmovie2.mvp.model.repository

import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.model.entity.FullMovie
import com.headmostlab.findmovie2.mvp.model.entity.Person
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getMovies(request: String, page: Int? = null): Single<List<ShortMovie>>
    fun getNowPlayingMovies(): Single<List<ShortMovie>>
    fun getUpcomingMovies(): Single<List<ShortMovie>>
    fun getPopularMovies(): Single<List<ShortMovie>>
    fun getMovie(movieId: Int): Single<FullMovie>
    fun getVideos(movieId: Int): Single<List<String>>
    fun getPeople(movieId: Int): Single<List<Person>>
    fun storeCollections(collections: List<Collection>): Completable
    fun getCollections(): Single<List<Collection>>
    fun getCollection(id: Int): Single<Collection>
}