package com.headmostlab.findmovie2.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.headmostlab.findmovie2.mvp.model.navigator.IScreens
import com.headmostlab.findmovie2.mvp.view.MainView
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.main())
    }

    fun onBackPressed() {
        router.exit()
    }
}