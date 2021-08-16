package com.headmostlab.findmovie2.ui.fragment

import android.widget.Toast
import com.headmostlab.findmovie2.R
import com.headmostlab.findmovie2.databinding.FragmentCollectionsBinding
import com.headmostlab.findmovie2.mvp.presenter.CollectionsPresenter
import com.headmostlab.findmovie2.mvp.view.CollectionsView
import com.headmostlab.findmovie2.ui.App
import com.headmostlab.findmovie2.ui.BackButtonListener
import com.headmostlab.findmovie2.ui.adapter.CollectionAdapter
import com.headmostlab.findmovie2.ui.adapter.CollectionsAdapter
import com.headmostlab.findmovie2.ui.decoration.ItemDecoration
import com.headmostlab.findmovie2.ui.utils.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CollectionsFragment : MvpAppCompatFragment(R.layout.fragment_collections), CollectionsView,
    BackButtonListener {

    private val presenter by moxyPresenter {
        CollectionsPresenter().apply { App.instance.appComponent.inject(this) }
    }

    private val binding by viewBinding(FragmentCollectionsBinding::bind)

    override fun init() {
        binding.collectionsRecyclerView.adapter = CollectionsAdapter(presenter.listPresenter) {
            CollectionAdapter(presenter.provideCollectionListPresenter()).apply {
                App.instance.appComponent.inject(this)
            }
        }

        val itemDecoration = ItemDecoration.Builder()
            .setBottom(resources.getDimensionPixelOffset(R.dimen.large_margin))
            .setFirstLast(resources.getDimensionPixelOffset(R.dimen.the_largest_margin))
            .build()

        binding.collectionsRecyclerView.addItemDecoration(itemDecoration)
    }

    override fun updateList() {
        binding.collectionsRecyclerView.adapter?.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}