package com.headmostlab.findmovie2.ui.fragment

import androidx.core.os.bundleOf
import com.headmostlab.findmovie2.R
import com.headmostlab.findmovie2.databinding.FragmentMovieBinding
import com.headmostlab.findmovie2.mvp.model.entity.FullMovie
import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import com.headmostlab.findmovie2.mvp.presenter.MoviePresenter
import com.headmostlab.findmovie2.mvp.view.MovieView
import com.headmostlab.findmovie2.ui.App
import com.headmostlab.findmovie2.ui.utils.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class MovieFragment : MvpAppCompatFragment(R.layout.fragment_movie), MovieView {

    companion object {
        private const val ARGUMENT_MOVIE = "MOVIE"

        fun newInstance(movie: ShortMovie): MovieFragment =
            MovieFragment().apply {
                arguments = bundleOf(ARGUMENT_MOVIE to movie)
            }
    }

    private val binding by viewBinding(FragmentMovieBinding::bind)

    private val presenter by moxyPresenter {
        MoviePresenter(arguments?.getParcelable(ARGUMENT_MOVIE)).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun showMovie(movie: FullMovie) {
        val context = binding.root.context
        with(binding) {
            title.text = movie.title
            genres.text = movie.genres.joinToString()
            duration.text = context.getString(R.string.detail_duration, movie.duration.toString())
            rating.text = context.getString(
                R.string.detail_rating,
                movie.votesAverage.toString()
            )
            date.text = if (movie.date.length > 4) movie.date.subSequence(0..3) else movie.date
            description.text = movie.description
        }
//        actorAdapter.submitList(movie.people)
    }

    override fun init() {
    }

}