package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.CountersModel
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.MainView


class MainPresenter(val view: MainView) {

    val model = CountersModel()

    fun onButton1Clicked() {
        val nextValue = model.next(0)
        view.showText1(nextValue.toString())
    }

    fun onButton2Clicked() {
        val nextValue = model.next(1)
        view.showText2(nextValue.toString())
    }

    fun onButton3Clicked() {
        val nextValue = model.next(2)
        view.showText3(nextValue.toString())
    }

}