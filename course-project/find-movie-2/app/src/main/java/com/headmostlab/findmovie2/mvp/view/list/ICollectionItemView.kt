package com.headmostlab.findmovie2.mvp.view.list

import com.headmostlab.findmovie2.mvp.presenter.list.ICollectionListPresenter

interface ICollectionItemView : IItemView {
    fun setPresenter(presenter: ICollectionListPresenter)
    fun updateList()
}