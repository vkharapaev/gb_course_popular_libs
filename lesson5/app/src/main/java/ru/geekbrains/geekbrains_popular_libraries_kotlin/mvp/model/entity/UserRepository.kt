package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepository(
    @Expose val id: Int,
    @Expose val name: String
) : Parcelable
