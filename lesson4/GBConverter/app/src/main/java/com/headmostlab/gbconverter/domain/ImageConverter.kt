package com.headmostlab.gbconverter.domain

import io.reactivex.rxjava3.core.Single
import java.io.File

interface ImageConverter {
    fun convert(image: Image, saveToPath: File): Single<File>
}