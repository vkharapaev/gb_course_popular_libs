package com.headmostlab.findmovie2.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}