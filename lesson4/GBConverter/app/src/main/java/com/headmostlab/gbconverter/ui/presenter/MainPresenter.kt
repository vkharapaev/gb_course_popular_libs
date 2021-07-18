package com.headmostlab.gbconverter.ui.presenter

import com.headmostlab.gbconverter.domain.Image
import com.headmostlab.gbconverter.domain.ImageConverter
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import java.io.File

class MainPresenter(private val converter: ImageConverter, private val uiScheduler: Scheduler) :
    MvpPresenter<MainView>() {

    private var sourceImage: Image? = null
    private var disposable: Disposable? = null

    fun convert(storeToPath: File) {
        if (sourceImage == null) {
            viewState.showMessageChooseImage()
        }

        val image = sourceImage ?: return

        val disposable = this.disposable

        if (disposable != null && !disposable.isDisposed) {
            cancel(disposable)
        } else {
            viewState.showTimer()
            viewState.showCancelButton()
            this.disposable =
                converter.convert(image, storeToPath).observeOn(uiScheduler).subscribe(
                    {
                        viewState.showConvertedImage(it)
                        viewState.showConvertButton()
                    },
                    {
                        viewState.showError(it)
                        viewState.showPlaceholder()
                        viewState.showConvertButton()
                    }
                )
        }
    }

    fun onImageSelected(image: Image) {
        sourceImage = image
        viewState.showChosenImage(image)
    }

    fun onChooseImage() {
        viewState.chooseImage()
    }

    private fun cancel(disposable: Disposable) {
        disposable.dispose()
        viewState.showPlaceholder()
        viewState.showConvertButton()
    }
}