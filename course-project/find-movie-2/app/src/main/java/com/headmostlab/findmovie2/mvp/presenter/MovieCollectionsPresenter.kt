package com.headmostlab.findmovie2.mvp.presenter

import com.headmostlab.findmovie2.mvp.model.repository.Repository
import com.headmostlab.findmovie2.mvp.presenter.list.IMovieCollectionsListPresenter
import com.headmostlab.findmovie2.mvp.view.MovieCollectionsView
import com.headmostlab.findmovie2.mvp.view.list.IMovieCollectionsItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class MovieCollectionsPresenter : MvpPresenter<MovieCollectionsView>() {

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var uiScheduler: Scheduler

    val listPresenter: IMovieCollectionsListPresenter = ListPresenter()

    override fun onFirstViewAttach() {
        viewState.init()

        repository.getCollections().observeOn(uiScheduler)
    }
}

private class ListPresenter : IMovieCollectionsListPresenter {

    override var itemClickListener: ((IMovieCollectionsItemView) -> Unit)? = null

    override fun bindView(view: IMovieCollectionsItemView) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }
}