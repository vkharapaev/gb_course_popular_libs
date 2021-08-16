package com.headmostlab.findmovie2.mvp.model.resource

import com.headmostlab.findmovie2.mvp.model.entity.ECollection

interface ResourceManager {
    fun getString(collection: ECollection): String
}