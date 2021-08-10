package com.headmostlab.findmovie2.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CollectionsView : MvpView {
    fun init()
    fun updateList()
    fun showMessage(message: String)
}