package com.headmostlab.findmovie2.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.model.entity.ECollection
import com.headmostlab.findmovie2.mvp.model.navigator.Screens
import com.headmostlab.findmovie2.mvp.model.repository.Repository
import com.headmostlab.findmovie2.mvp.model.repository.SharedPreferencesRepository
import com.headmostlab.findmovie2.mvp.view.MainView
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: Screens

    @Inject
    lateinit var sharedPrefRepo: SharedPreferencesRepository

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var uiScheduler: Scheduler

    private val collections by lazy {
        ECollection.values().map { Collection(it.ordinal + 1, it, it.request) }
    }

    private val compositeDisposable = CompositeDisposable()

    private val observer = object : CompletableObserver {
        override fun onComplete() {
            sharedPrefRepo.recordAppFirstStart()
            router.replaceScreen(screens.collections())
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }

        override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        isAppFirstStart().observeOn(uiScheduler).subscribe(observer)
    }

    private fun isAppFirstStart() =
        if (sharedPrefRepo.isAppFirstStart()) {
            repository.storeCollections(collections)
        } else {
            Completable.complete()
        }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    fun onBackPressed() {
        router.exit()
    }
}