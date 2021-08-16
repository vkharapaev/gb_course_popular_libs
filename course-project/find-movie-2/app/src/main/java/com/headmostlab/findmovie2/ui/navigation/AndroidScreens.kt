package com.headmostlab.findmovie2.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import com.headmostlab.findmovie2.mvp.model.navigator.Screens
import com.headmostlab.findmovie2.ui.fragment.CollectionFragment
import com.headmostlab.findmovie2.ui.fragment.CollectionsFragment
import com.headmostlab.findmovie2.ui.fragment.MovieFragment

class AndroidScreens : Screens {

    override fun collections(): Screen =
        FragmentScreen { CollectionsFragment() }

    override fun collection(collection: Collection): Screen =
        FragmentScreen { CollectionFragment.newInstance(collection) }

    override fun movie(movie: ShortMovie): Screen =
        FragmentScreen {MovieFragment.newInstance(movie)}

}