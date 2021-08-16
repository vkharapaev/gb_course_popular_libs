package com.headmostlab.findmovie2.mvp.presenter

import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import com.headmostlab.findmovie2.mvp.model.image.ImageHostProvider
import com.headmostlab.findmovie2.mvp.model.repository.Repository
import com.headmostlab.findmovie2.mvp.model.resource.ResourceManager
import com.headmostlab.findmovie2.mvp.presenter.entity.UiCollection
import com.headmostlab.findmovie2.mvp.presenter.list.CollectionListPresenter
import com.headmostlab.findmovie2.mvp.presenter.list.CollectionsListPresenter
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

    @Inject
    lateinit var imageHostProvider: ImageHostProvider

    @Inject
    lateinit var resourceManager: ResourceManager

    private val compositeDisposable = CompositeDisposable()

    private val _listPresenter = CollectionsListPresenterImpl()

    val listPresenter: CollectionsListPresenter = _listPresenter

    override fun onFirstViewAttach() {
        viewState.init()

        val disposable = loadMovies {
            _listPresenter.setData(it)
            viewState.updateList()
        }

        compositeDisposable.add(disposable)
    }

    private fun loadMovies(onSuccess: (List<UiCollection>) -> Unit): Disposable {
        return repository.getCollections()
            .flatMap { collections ->
                val movieLists = collections.map { collection ->
                    repository.getMovies(collection).map { it.take(10) }
                        .map { UiCollection(collection.eCollection, it) }
                }
                Single.zip(movieLists) { it.toList() as List<UiCollection> }
            }
            .observeOn(uiScheduler)
            .subscribe({ onSuccess(it) }, { it.printStackTrace() })
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    fun provideCollectionListPresenter(): CollectionListPresenter = CollectionListPresenterImpl()

    // ---------------------------------------------------------------------------------------------
    private inner class CollectionsListPresenterImpl : CollectionsListPresenter {

        private val uiCollectionList = mutableListOf<UiCollection>()
        private val collectionListPresenters = mutableListOf<CollectionListPresenterImpl>()

        fun setData(data: List<UiCollection>) {
            uiCollectionList.clear()
            uiCollectionList.addAll(data)
            collectionListPresenters.clear()
            data.forEach {
                collectionListPresenters.add(CollectionListPresenterImpl().apply { setData(it.movies) })
            }
        }

        override var itemClickListener: ((ICollectionItemView) -> Unit)? = {
            viewState.showMessage(resourceManager.getString(uiCollectionList[it.position()].eCollection))
        }

        override fun bindView(view: ICollectionItemView) {
            if (view.position() == IListPresenter.NO_POSITION) return
            val uiCollection = uiCollectionList[view.position()]
            view.setTitle(resourceManager.getString(uiCollection.eCollection))
            itemClickListener?.let { view.setListener(it) }
            (view.presenter as? CollectionListPresenterImpl)?.setData(uiCollection.movies)
            view.updateList()
        }

        override fun getCount(): Int = collectionListPresenters.size
    }

    // ---------------------------------------------------------------------------------------------
    private inner class CollectionListPresenterImpl : CollectionListPresenter {

        private val collection = mutableListOf<ShortMovie>()

        fun setData(data: List<ShortMovie>) {
            collection.clear()
            collection.addAll(data)
        }

        override var itemClickListener: ((IMovieItemView) -> Unit)? = { view ->
            viewState.showMessage(collection[view.position()].title)
        }

        override fun bindView(view: IMovieItemView) {
            if (view.position() == IListPresenter.NO_POSITION) return
            val movie = collection[view.position()]
            view.setTitle(movie.title)
            itemClickListener?.let { view.setListener(it) }
            movie.poster?.let {
                view.loadPoster(imageHostProvider.getHostUrl() + it)
            }
        }

        override fun getCount(): Int = collection.size
    }
}
