package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.ItemUserBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list.IUserListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.UserItemView

class UsersRVAdapter(val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder)

    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(val vb: ItemUserBinding) :
        RecyclerView.ViewHolder(vb.root), UserItemView {

        override fun getPos(): Int {
            return adapterPosition
        }

        override fun setLogin(text: String) {
            vb.tvLogin.text = text
        }

        override fun setClickListener(listener: ((UserItemView) -> Unit)?) {
            vb.item.setOnClickListener {
                listener?.invoke(this)
            }
        }
    }

}