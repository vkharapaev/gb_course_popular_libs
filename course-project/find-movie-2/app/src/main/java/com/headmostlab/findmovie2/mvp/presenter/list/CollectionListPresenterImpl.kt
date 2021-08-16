package com.headmostlab.findmovie2.mvp.presenter.list

import com.headmostlab.findmovie2.mvp.model.entity.ShortMovie
import com.headmostlab.findmovie2.mvp.model.image.ImageHostProvider
import com.headmostlab.findmovie2.mvp.view.list.IMovieItemView

class CollectionListPresenterImpl(
    private val imageHostProvider: ImageHostProvider,
    private val movieSelectedListener: (ShortMovie) -> Unit
) :
    CollectionListPresenter {

    private val collection = mutableListOf<ShortMovie>()

    fun setData(data: List<ShortMovie>) {
        collection.clear()
        collection.addAll(data)
    }

    override var itemClickListener: ((IMovieItemView) -> Unit)? = { view ->
        movieSelectedListener(collection[view.position()])
    }

    override fun bindView(view: IMovieItemView) {
        if (view.position() == IListPresenter.NO_POSITION) return
        val movie = collection[view.position()]
        view.setTitle(movie.title)
        itemClickListener?.let { view.setListener(it) }
        movie.poster?.let {
            view.loadPoster(imageHostProvider.getHostUrl() + it)
        }
    }

    override fun getCount(): Int = collection.size
}