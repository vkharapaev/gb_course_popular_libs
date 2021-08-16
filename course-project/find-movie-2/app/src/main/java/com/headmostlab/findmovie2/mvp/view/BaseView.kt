package com.headmostlab.findmovie2.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BaseView : MvpView {
    @StateStrategyType(SingleStateStrategy::class)
    fun init()
}