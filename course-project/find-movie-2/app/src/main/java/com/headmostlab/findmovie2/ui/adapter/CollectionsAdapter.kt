package com.headmostlab.findmovie2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.headmostlab.findmovie2.R
import com.headmostlab.findmovie2.databinding.ItemCollectionBinding
import com.headmostlab.findmovie2.mvp.presenter.list.CollectionListPresenter
import com.headmostlab.findmovie2.mvp.presenter.list.CollectionsListPresenter
import com.headmostlab.findmovie2.mvp.view.list.ICollectionItemView
import com.headmostlab.findmovie2.ui.decoration.ItemDecoration

class CollectionsAdapter(
    private val presenter: CollectionsListPresenter,
    private val collectionAdapterFactory: (() -> CollectionAdapter)
) :
    RecyclerView.Adapter<CollectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CollectionViewHolder.newInstance(collectionAdapterFactory.invoke(), parent)

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) =
        presenter.bindView(holder)

    override fun getItemCount(): Int = presenter.getCount()
}

class CollectionViewHolder(
    private val adapter: CollectionAdapter,
    private val binding: ItemCollectionBinding,
) :
    RecyclerView.ViewHolder(binding.root), ICollectionItemView {

    override val presenter: CollectionListPresenter = adapter.presenter

    init {
        binding.recyclerView.adapter = adapter

        val decoration = ItemDecoration.Builder()
            .setRight(binding.root.context.resources.getDimensionPixelOffset(R.dimen.large_margin))
            .setFirstLast(binding.root.context.resources.getDimensionPixelOffset(R.dimen.large_margin))
            .build()

        binding.recyclerView.addItemDecoration(decoration)
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun setTitle(title: String) {
        binding.title.text = title
    }

    override fun position(): Int = bindingAdapterPosition

    companion object {
        fun newInstance(
            adapter: CollectionAdapter,
            parent: ViewGroup,
        ) = CollectionViewHolder(
            adapter,
            ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}