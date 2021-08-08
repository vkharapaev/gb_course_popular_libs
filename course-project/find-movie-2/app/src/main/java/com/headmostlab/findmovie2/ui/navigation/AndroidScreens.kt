package com.headmostlab.findmovie2.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.headmostlab.findmovie2.mvp.model.navigator.IScreens
import com.headmostlab.findmovie2.ui.fragment.MovieCollectionsFragment

class AndroidScreens : IScreens {
    override fun main(): Screen = FragmentScreen { MovieCollectionsFragment() }

}