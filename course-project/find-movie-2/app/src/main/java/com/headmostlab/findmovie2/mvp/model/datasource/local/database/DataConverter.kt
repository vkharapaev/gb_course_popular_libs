package com.headmostlab.findmovie2.mvp.model.datasource.local.database

import com.headmostlab.findmovie2.mvp.model.datasource.local.database.entities.Collection
import com.headmostlab.findmovie2.mvp.model.datasource.local.database.entities.Movie
import com.headmostlab.findmovie2.mvp.model.entity.ECollection
import com.headmostlab.findmovie2.mvp.model.entity.FullMovie
import com.headmostlab.findmovie2.mvp.model.entity.Person
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

    fun toShortMovies(movies: List<Movie>): List<ShortMovie> = movies.map { map(it) }

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

    fun map(id: Int, collection: com.headmostlab.findmovie2.mvp.model.entity.Collection) =
        Collection(
            id,
            collection.eCollection.name,
            collection.eCollection.request
        )

    fun map(movie: FullMovie) =
        com.headmostlab.findmovie2.mvp.model.datasource.local.database.entities.FullMovie(
            movie.id,
            movie.title,
            movie.origTitle,
//            movie.genres,
            movie.duration,
            movie.rating,
            movie.votesAverage,
            movie.votesCount,
            movie.budget,
            movie.revenue,
            movie.date,
            movie.description,
            movie.poster,
//            movie.people
        )

    fun map(movie: com.headmostlab.findmovie2.mvp.model.datasource.local.database.entities.FullMovie) =
        FullMovie(
            movie.id,
            movie.title,
            movie.origTitle,
            listOf(),     // movie.genres,
            movie.duration,
            movie.rating,
            movie.votesAverage,
            movie.votesCount,
            movie.budget,
            movie.revenue,
            movie.date,
            movie.description,
            movie.poster,
            null   // movie.people
        )

}
