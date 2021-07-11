package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserView

class UserPresenter(private val router: Router) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        viewState.init()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}