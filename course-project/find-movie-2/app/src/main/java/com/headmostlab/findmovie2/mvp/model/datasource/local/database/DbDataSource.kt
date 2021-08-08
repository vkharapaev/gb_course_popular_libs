package com.headmostlab.findmovie2.mvp.model.datasource.local.database

import com.headmostlab.findmovie2.mvp.model.entity.Collection
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class DbDataSource(private val db: RoomDb) {
    fun getCollections(): Single<List<Collection>> =
        db.collectionDao().getAll().subscribeOn(Schedulers.io())
            .map { it -> it.map { DataConverter.map(it) } }

    fun getCollection(id: Int): Single<Collection> =
        db.collectionDao().get(id).subscribeOn(Schedulers.io()).map { DataConverter.map(it) }

    fun storeCollections(collections: List<Collection>): Completable =
        db.collectionDao().insertAll(collections.mapIndexed { idx, c -> DataConverter.map(idx, c) })
            .subscribeOn(Schedulers.io())
}