package com.headmostlab.findmovie2.ui.fragment

import androidx.fragment.app.Fragment
import com.headmostlab.findmovie2.R
import com.headmostlab.findmovie2.databinding.FragmentMovieCollectionsBinding
import com.headmostlab.findmovie2.di.moviecollections.MovieCollectionsSubcomponent
import com.headmostlab.findmovie2.mvp.presenter.MovieCollectionsPresenter
import com.headmostlab.findmovie2.mvp.view.MovieCollectionsView
import com.headmostlab.findmovie2.ui.App
import com.headmostlab.findmovie2.ui.utils.viewBinding

class MovieCollectionsFragment : Fragment(R.layout.fragment_movie_collections),
    MovieCollectionsView {

    private lateinit var movieCollectionsSubcomponent: MovieCollectionsSubcomponent

    private val presenter by lazy {
        MovieCollectionsPresenter().apply {
            movieCollectionsSubcomponent = App.instance.appComponent.movieCollectionsSubcomponent()
            movieCollectionsSubcomponent.inject(this)
        }
    }

    private val binding by viewBinding(FragmentMovieCollectionsBinding::bind)

    override fun init() {

    }

}