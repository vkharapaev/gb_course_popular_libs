package com.headmostlab.findmovie2.mvp.presenter

import com.headmostlab.findmovie2.mvp.view.MovieCollectionsView
import moxy.MvpPresenter

class MovieCollectionsPresenter : MvpPresenter<MovieCollectionsView>() {

    override fun onFirstViewAttach() {
        viewState.init()
    }

}