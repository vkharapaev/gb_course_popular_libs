package com.headmostlab.findmovie2.mvp.model.datasource.local.database

import com.headmostlab.findmovie2.mvp.model.datasource.local.database.entities.CollectionMovieCrossRef
import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.model.entity.FullMovie
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class DbDataSource(private val db: RoomDb) {
    fun getCollections(): Single<List<Collection>> =
        db.collectionDao().getAll().map { it -> it.map { DataConverter.map(it) } }

    fun getCollection(id: Int): Single<Collection> =
        db.collectionDao().get(id).map { DataConverter.map(it) }

    fun storeCollections(collections: List<Collection>): Completable =
        db.collectionDao().insertAll(collections.mapIndexed { idx, c -> DataConverter.map(idx, c) })

    fun storeMovies(request: String, movies: List<ShortMovie>, page: Int = 0): Completable {
        return Completable.fromAction {
            val dbCollection = db.collectionDao().getByRequest(request)
            val collectionMovieCrossRefs =
                movies.map { CollectionMovieCrossRef(dbCollection.id, page, it.id) }
            db.runInTransaction {
                db.movieDao().insertAll(DataConverter.map(movies))
                db.collectionMovieCrossRefDao().insertAll(collectionMovieCrossRefs)
            }
        }
    }

    fun getMovies(collection: Collection): Single<List<ShortMovie>> {
        return db.collectionDao().getByRequestSingle(collection.request).flatMap { dbCollection ->
            db.movieDao().getMovies(dbCollection.id).map { DataConverter.toShortMovies(it) }
        }
    }


    fun storeFullMovie(movie: FullMovie) =
        db.fullMovieDao().insert(DataConverter.map(movie))

    fun getFullMovie(movieId: Int): Single<FullMovie> =
        db.fullMovieDao().get(movieId).map(DataConverter::map)
}