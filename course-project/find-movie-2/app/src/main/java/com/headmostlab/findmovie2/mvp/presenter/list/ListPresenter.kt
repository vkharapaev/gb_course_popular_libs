package com.headmostlab.findmovie2.mvp.presenter.list

interface IListPresenter<V> {
    companion object {
       const val NO_POSITION = -1
    }
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}