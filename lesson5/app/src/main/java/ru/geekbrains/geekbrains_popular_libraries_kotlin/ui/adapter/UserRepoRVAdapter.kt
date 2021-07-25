package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.ItemRepositoryBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list.IRepoListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.RepoItemView

class UserRepoRVAdapter(private val presenter: IRepoListPresenter) :
    RecyclerView.Adapter<UserRepoRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    class ViewHolder(val vb: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(vb.root), RepoItemView {

        override fun setName(name: String) {
            vb.tvName.text = name
        }

        override fun getPos() = adapterPosition

        companion object {
            @JvmStatic
            fun create(parent: ViewGroup) =
                ViewHolder(
                    ItemRepositoryBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
        }
    }
}