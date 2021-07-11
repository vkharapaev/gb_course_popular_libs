package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun setClickListener(listener: ((UserItemView) -> Unit)?)
}