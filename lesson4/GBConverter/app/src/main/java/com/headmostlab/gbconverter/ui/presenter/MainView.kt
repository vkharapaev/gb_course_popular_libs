package com.headmostlab.gbconverter.ui.presenter

import com.headmostlab.gbconverter.domain.Image
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.io.File

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun chooseImage()
    fun showChosenImage(image: Image)
    fun showTimer()
    fun showPlaceholder()
    fun showConvertedImage(image: File)
    fun showError(error: Throwable?)
    fun showConvertButton()
    fun showCancelButton()
    fun showMessageChooseImage()
}