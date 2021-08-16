package com.headmostlab.findmovie2.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import com.headmostlab.findmovie2.mvp.model.image.ImageHostProvider
import com.headmostlab.findmovie2.mvp.model.navigator.Screens
import com.headmostlab.findmovie2.mvp.model.repository.Repository
import com.headmostlab.findmovie2.mvp.presenter.list.CollectionListPresenter
import com.headmostlab.findmovie2.mvp.presenter.list.CollectionListPresenterImpl
import com.headmostlab.findmovie2.mvp.view.CollectionView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class CollectionPresenter(collectionArg: Collection?) : MvpPresenter<CollectionView>() {

    private val collection: Collection =
        collectionArg ?: throw IllegalArgumentException("Collection is not provided")

    private val _listPresenter by lazy {
        CollectionListPresenterImpl(imageHostProvider, ::goToMovieScreen)
    }

    val listPresenter: CollectionListPresenter by lazy { _listPresenter }

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: Screens

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var imageHostProvider: ImageHostProvider

    override fun onFirstViewAttach() {
        viewState.init()

        repository.getMovies(collection).observeOn(uiScheduler).subscribe({ movies ->
            _listPresenter.setData(movies)
            viewState.updateList()
        }, {
            it.printStackTrace()
        })
    }

    private fun goToMovieScreen(movie: ShortMovie) = router.navigateTo(screens.movie(movie))

    fun backPressed() = router.exit()

}