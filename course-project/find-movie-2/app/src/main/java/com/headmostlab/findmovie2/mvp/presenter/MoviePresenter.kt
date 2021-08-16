package com.headmostlab.findmovie2.mvp.presenter

import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import com.headmostlab.findmovie2.mvp.model.repository.Repository
import com.headmostlab.findmovie2.mvp.view.MovieView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MoviePresenter(argMovie: ShortMovie?) : MvpPresenter<MovieView>() {

    private val movie = argMovie ?: throw IllegalArgumentException("Movie is not provided")

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var uiScheduler: Scheduler

    override fun onFirstViewAttach() {
        viewState.init()

        repository.getMovie(movie.id).observeOn(uiScheduler).subscribe({
            viewState.showMovie(it)
        }, {
            it.printStackTrace()
        })
    }

}