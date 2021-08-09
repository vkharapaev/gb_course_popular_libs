package com.headmostlab.findmovie2.ui.fragment

import com.headmostlab.findmovie2.R
import com.headmostlab.findmovie2.databinding.FragmentCollectionsBinding
import com.headmostlab.findmovie2.di.moviecollections.MovieCollectionsSubcomponent
import com.headmostlab.findmovie2.mvp.presenter.CollectionsPresenter
import com.headmostlab.findmovie2.mvp.view.CollectionsView
import com.headmostlab.findmovie2.ui.App
import com.headmostlab.findmovie2.ui.adapter.CollectionsAdapter
import com.headmostlab.findmovie2.ui.utils.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CollectionsFragment : MvpAppCompatFragment(R.layout.fragment_collections),
    CollectionsView {

    private lateinit var movieCollectionsSubcomponent: MovieCollectionsSubcomponent

    private val presenter by moxyPresenter {
        CollectionsPresenter().apply {
            movieCollectionsSubcomponent = App.instance.appComponent.movieCollectionsSubcomponent()
            movieCollectionsSubcomponent.inject(this)
        }
    }

    private val binding by viewBinding(FragmentCollectionsBinding::bind)

    override fun init() {
        binding.movieCollectionsRecyclerView.adapter =
            CollectionsAdapter(presenter.listPresenter)
    }

    override fun updateList() {
        binding.movieCollectionsRecyclerView.adapter?.notifyDataSetChanged()
    }
}