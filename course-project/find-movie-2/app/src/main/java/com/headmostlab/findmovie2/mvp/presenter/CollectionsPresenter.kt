package com.headmostlab.findmovie2.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import com.headmostlab.findmovie2.mvp.model.image.ImageHostProvider
import com.headmostlab.findmovie2.mvp.model.navigator.Screens
import com.headmostlab.findmovie2.mvp.model.repository.Repository
import com.headmostlab.findmovie2.mvp.model.resource.ResourceManager
import com.headmostlab.findmovie2.mvp.presenter.entity.UiCollection
import com.headmostlab.findmovie2.mvp.presenter.list.CollectionListPresenter
import com.headmostlab.findmovie2.mvp.presenter.list.CollectionListPresenterImpl
import com.headmostlab.findmovie2.mvp.presenter.list.CollectionsListPresenter
import com.headmostlab.findmovie2.mvp.presenter.list.IListPresenter
import com.headmostlab.findmovie2.mvp.view.CollectionsView
import com.headmostlab.findmovie2.mvp.view.list.ICollectionItemView
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

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: Screens

    private val compositeDisposable = CompositeDisposable()

    private val _listPresenter = CollectionsListPresenterImpl(::goToCollectionScreen)

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
                        .map { UiCollection(collection, it) }
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

    private fun goToCollectionScreen(collection: Collection) =
        router.navigateTo(screens.collection(collection))

    private fun gotToMovieScreen(movie: ShortMovie) =
        router.navigateTo(screens.movie(movie))

    fun provideCollectionListPresenter(): CollectionListPresenter =
        CollectionListPresenterImpl(imageHostProvider, ::gotToMovieScreen)

    fun backPressed() = router.exit()

    private inner class CollectionsListPresenterImpl(private val collectionSelectedListener: (Collection) -> Unit) :
        CollectionsListPresenter {

        private val uiCollectionList = mutableListOf<UiCollection>()

        fun setData(data: List<UiCollection>) {
            uiCollectionList.clear()
            uiCollectionList.addAll(data)
        }

        override var itemClickListener: ((ICollectionItemView) -> Unit)? = {
            collectionSelectedListener(uiCollectionList[it.position()].collection)
        }

        override fun bindView(view: ICollectionItemView) {
            if (view.position() == IListPresenter.NO_POSITION) return
            val uiCollection = uiCollectionList[view.position()]
            view.setTitle(resourceManager.getString(uiCollection.collection.eCollection))
            itemClickListener?.let { view.setListener(it) }
            (view.presenter as? CollectionListPresenterImpl)?.setData(uiCollection.movies)
            view.updateList()
        }

        override fun getCount(): Int = uiCollectionList.size
    }
}
