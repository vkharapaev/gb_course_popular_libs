package com.headmostlab.findmovie2.ui.fragment

import androidx.core.os.bundleOf
import com.headmostlab.findmovie2.R
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import moxy.MvpAppCompatFragment

class MovieFragment : MvpAppCompatFragment(R.layout.fragment_movie) {
    companion object {

        private const val ARGUMENT_MOVIE = "MOVIE"

        fun newInstance(movie: ShortMovie): MovieFragment =
            MovieFragment().apply {
                arguments = bundleOf(ARGUMENT_MOVIE to movie)
            }
    }
}