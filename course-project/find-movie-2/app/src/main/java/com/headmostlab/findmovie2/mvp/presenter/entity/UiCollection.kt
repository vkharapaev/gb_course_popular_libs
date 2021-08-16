package com.headmostlab.findmovie2.mvp.presenter.entity

import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie

data class UiCollection(val collection: Collection, val movies: List<ShortMovie>)
