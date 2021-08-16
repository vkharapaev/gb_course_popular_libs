package com.headmostlab.findmovie2.mvp.model.image

interface ImageLoader<T> {
    fun loadInto(url: String, container: T)
}