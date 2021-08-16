package com.headmostlab.findmovie2.mvp.view

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CollectionsView : BaseView {
    fun updateList()
    fun showMessage(message: String)
}