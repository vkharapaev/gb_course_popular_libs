package com.headmostlab.findmovie2.mvp.model.navigator

import com.github.terrakok.cicerone.Screen
import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie

interface Screens {
    fun collections(): Screen
    fun collection(collection: Collection): Screen
    fun movie(movie: ShortMovie): Screen
}