package com.headmostlab.findmovie2.mvp.view.list

import com.headmostlab.findmovie2.mvp.presenter.list.CollectionListPresenter

interface ICollectionItemView : IItemView {
    val presenter: CollectionListPresenter
    fun updateList()
    fun setTitle(title: String)
}