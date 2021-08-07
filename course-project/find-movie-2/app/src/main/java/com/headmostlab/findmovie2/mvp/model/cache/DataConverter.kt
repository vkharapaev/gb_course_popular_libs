package com.headmostlab.findmovie2.mvp.model.cache

import com.headmostlab.findmovie2.mvp.model.cache.entities.Collection
import com.headmostlab.findmovie2.mvp.model.cache.entities.Movie
import com.headmostlab.findmovie2.mvp.model.entity.ECollection
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie

object DataConverter {

    fun map(movie: ShortMovie) =
        Movie(
            movie.id,
            movie.title,
            movie.date,
            movie.rating,
            movie.popularity,
            movie.poster,
            movie.backdrop
        )

    fun map(movies: List<ShortMovie>): List<Movie> = movies.map { map(it) }

    fun map(movie: Movie) =
        ShortMovie(
            movie.id,
            movie.title,
            movie.date,
            movie.rating,
            movie.popularity,
            movie.poster,
            movie.backdrop
        )

    fun map(collection: Collection) =
        com.headmostlab.findmovie2.mvp.model.entity.Collection(
            collection.id,
            ECollection.valueOf(collection.collectionRid),
            collection.request
        )
}
