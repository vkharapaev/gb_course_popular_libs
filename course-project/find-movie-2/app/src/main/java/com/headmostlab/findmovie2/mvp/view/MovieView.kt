package com.headmostlab.findmovie2.mvp.view

import com.headmostlab.findmovie2.mvp.model.entity.FullMovie
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface MovieView : BaseView {
    fun showMovie(movie: FullMovie)
}