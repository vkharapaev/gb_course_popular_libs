package com.headmostlab.findmovie2.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.model.entity.ECollection
import com.headmostlab.findmovie2.mvp.model.navigator.IScreens
import com.headmostlab.findmovie2.mvp.model.repository.Repository
import com.headmostlab.findmovie2.mvp.model.repository.SharedPreferencesRepository
import com.headmostlab.findmovie2.mvp.view.MainView
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var sharedPrefRepo: SharedPreferencesRepository

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var uiScheduler: Scheduler

    private val collections by lazy {
        ECollection.values().map { Collection(it.ordinal + 1, it, it.request) }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val completable = if (sharedPrefRepo.isAppFirstStart()) {
            repository.storeCollections(collections)
        } else {
            Completable.complete()
        }

        completable.observeOn(uiScheduler).subscribe({
            sharedPrefRepo.recordAppFirstStart()
            router.replaceScreen(screens.main())
        }, { it.printStackTrace() })
    }

    fun onBackPressed() {
        router.exit()
    }
}