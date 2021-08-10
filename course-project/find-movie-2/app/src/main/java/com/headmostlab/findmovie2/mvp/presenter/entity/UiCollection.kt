package com.headmostlab.findmovie2.mvp.presenter.entity

import com.headmostlab.findmovie2.mvp.model.entity.ECollection
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie

data class UiCollection(val eCollection: ECollection, val movies: List<ShortMovie>)
