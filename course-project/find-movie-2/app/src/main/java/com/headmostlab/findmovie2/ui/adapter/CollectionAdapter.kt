package com.headmostlab.findmovie2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.headmostlab.findmovie2.databinding.ItemMovieBinding
import com.headmostlab.findmovie2.mvp.model.image.ImageLoader
import com.headmostlab.findmovie2.mvp.presenter.list.CollectionListPresenter
import com.headmostlab.findmovie2.mvp.view.list.IMovieItemView
import javax.inject.Inject

class CollectionAdapter(val presenter: CollectionListPresenter) :
    RecyclerView.Adapter<MovieViewHolder>() {

    @Inject
    lateinit var imageLoader: ImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder.newInstance(parent, imageLoader)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()
}

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val imageLoader: ImageLoader<ImageView>
) : RecyclerView.ViewHolder(binding.root), IMovieItemView {

    override fun setTitle(title: String) {
        binding.title.text = title
    }

    override fun loadPoster(url: String) {
        imageLoader.loadInto(url, binding.posterImage)
    }

    override fun setListener(listener: (IMovieItemView) -> Unit) {
        binding.card.setOnClickListener {
            listener.invoke(this)
        }
    }

    override fun position(): Int = bindingAdapterPosition

    companion object {
        fun newInstance(
            parent: ViewGroup,
            imageLoader: ImageLoader<ImageView>
        ) = MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            imageLoader
        )
    }
}