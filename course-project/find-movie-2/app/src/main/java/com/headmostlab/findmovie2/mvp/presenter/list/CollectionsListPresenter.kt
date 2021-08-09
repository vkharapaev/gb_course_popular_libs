package com.headmostlab.findmovie2.mvp.presenter.list

import com.headmostlab.findmovie2.mvp.view.list.IMovieItemView
import com.headmostlab.findmovie2.mvp.view.list.ICollectionItemView

interface ICollectionsListPresenter : IListPresenter<ICollectionItemView>
interface ICollectionListPresenter : IListPresenter<IMovieItemView>