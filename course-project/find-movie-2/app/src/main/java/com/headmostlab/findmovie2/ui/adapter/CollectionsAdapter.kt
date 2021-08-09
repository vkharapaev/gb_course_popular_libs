package com.headmostlab.findmovie2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.headmostlab.findmovie2.databinding.ItemCollectionBinding
import com.headmostlab.findmovie2.mvp.presenter.list.ICollectionListPresenter
import com.headmostlab.findmovie2.mvp.presenter.list.ICollectionsListPresenter
import com.headmostlab.findmovie2.mvp.view.list.ICollectionItemView

class CollectionsAdapter(private val presenter: ICollectionsListPresenter) :
    RecyclerView.Adapter<CollectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CollectionViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) =
        presenter.bindView(holder)

    override fun getItemCount(): Int = presenter.getCount()
}

class CollectionViewHolder(private val binding: ItemCollectionBinding) :
    RecyclerView.ViewHolder(binding.root), ICollectionItemView {

    private val adapter = CollectionAdapter()

    init {
        binding.moviesRecyclerView.adapter = adapter
    }

    override fun setPresenter(presenter: ICollectionListPresenter) {
        adapter.presenter = presenter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun position(): Int = bindingAdapterPosition

    companion object {
        fun newInstance(parent: ViewGroup) =
            CollectionViewHolder(
                ItemCollectionBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }
}