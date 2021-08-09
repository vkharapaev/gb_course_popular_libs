package com.headmostlab.findmovie2.mvp.presenter

import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import com.headmostlab.findmovie2.mvp.model.repository.Repository
import com.headmostlab.findmovie2.mvp.presenter.list.ICollectionListPresenter
import com.headmostlab.findmovie2.mvp.presenter.list.ICollectionsListPresenter
import com.headmostlab.findmovie2.mvp.presenter.list.IListPresenter
import com.headmostlab.findmovie2.mvp.view.CollectionsView
import com.headmostlab.findmovie2.mvp.view.list.ICollectionItemView
import com.headmostlab.findmovie2.mvp.view.list.IMovieItemView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import javax.inject.Inject

class CollectionsPresenter : MvpPresenter<CollectionsView>() {

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var uiScheduler: Scheduler

    private val compositeDisposable = CompositeDisposable()

    private val _listPresenter = CollectionsListPresenter()

    val listPresenter: ICollectionsListPresenter = _listPresenter

    override fun onFirstViewAttach() {
        viewState.init()

        val disposable = loadMovies {
            _listPresenter.setData(it)
            viewState.updateList()
        }

        compositeDisposable.add(disposable)
    }

    private fun loadMovies(onSuccess: (List<List<ShortMovie>>) -> Unit): Disposable {
        return repository.getCollections()
            .flatMap { collections ->
                val movieLists = collections.map {
                    repository.getMovies(it.request).map { it.take(10) }
                }
                Single.zip(movieLists) { it.toList() as List<List<ShortMovie>> }
            }
            .observeOn(uiScheduler)
            .subscribe({ onSuccess.invoke(it) }, { it.printStackTrace() })
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}

private class CollectionsListPresenter :
    ICollectionsListPresenter {

    private val collectionListPresenters = mutableListOf<CollectionListPresenter>()

    fun setData(data: List<List<ShortMovie>>) {
        collectionListPresenters.clear()
        data.forEach {
            collectionListPresenters.add(CollectionListPresenter().apply { setData(it) })
        }
    }

    override var itemClickListener: ((ICollectionItemView) -> Unit)? = null

    override fun bindView(view: ICollectionItemView) {
        if (view.position() == IListPresenter.NO_POSITION) return
        view.setPresenter(collectionListPresenters[view.position()])
        view.updateList()
    }

    override fun getCount(): Int = collectionListPresenters.size
}

private class CollectionListPresenter :
    ICollectionListPresenter {

    private val collection = mutableListOf<ShortMovie>()

    fun setData(data: List<ShortMovie>) {
        collection.clear()
        collection.addAll(data)
    }

    override var itemClickListener: ((IMovieItemView) -> Unit)? = null

    override fun bindView(view: IMovieItemView) {
        if (view.position() == IListPresenter.NO_POSITION) return
        val movie = collection[view.position()]
        view.setTitle(movie.title)
    }

    override fun getCount(): Int = collection.size
}