package com.headmostlab.findmovie2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.headmostlab.findmovie2.databinding.ItemMovieBinding
import com.headmostlab.findmovie2.mvp.presenter.list.ICollectionListPresenter
import com.headmostlab.findmovie2.mvp.view.list.IMovieItemView

class CollectionAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    var presenter: ICollectionListPresenter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        presenter?.bindView(holder)
    }

    override fun getItemCount(): Int {
        val size = presenter?.getCount() ?: 0
        return size
    }
}

class MovieViewHolder(
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root), IMovieItemView {

    override fun setTitle(title: String) {
        binding.movieTitle.text = title
    }

    override fun position(): Int = bindingAdapterPosition

    companion object {
        fun newInstance(parent: ViewGroup) =
            MovieViewHolder(
                ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }
}