package com.headmostlab.findmovie2.mvp.view.list

interface IMovieItemView : IItemView {
    fun setTitle(title: String)
    fun loadPoster(url: String)
    fun setListener(listener: ((IMovieItemView)) -> Unit)
}
