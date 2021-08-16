package com.headmostlab.findmovie2.ui.fragment

import androidx.core.os.bundleOf
import com.headmostlab.findmovie2.R
import com.headmostlab.findmovie2.databinding.FragmentCollectionBinding
import com.headmostlab.findmovie2.mvp.model.entity.Collection
import com.headmostlab.findmovie2.mvp.presenter.CollectionPresenter
import com.headmostlab.findmovie2.mvp.view.CollectionView
import com.headmostlab.findmovie2.ui.App
import com.headmostlab.findmovie2.ui.BackButtonListener
import com.headmostlab.findmovie2.ui.adapter.CollectionAdapter
import com.headmostlab.findmovie2.ui.utils.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CollectionFragment : MvpAppCompatFragment(R.layout.fragment_collection), CollectionView,
    BackButtonListener {

    companion object {
        private const val ARGUMENT_COLLECTION = "COLLECTION"

        fun newInstance(collection: Collection): CollectionFragment {
            return CollectionFragment().apply {
                arguments = bundleOf(ARGUMENT_COLLECTION to collection)
            }
        }
    }

    private val binding by viewBinding(FragmentCollectionBinding::bind)

    private val presenter by moxyPresenter {
        CollectionPresenter(arguments?.getParcelable(ARGUMENT_COLLECTION)).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun init() {
        binding.recyclerView.adapter = CollectionAdapter(presenter.listPresenter).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun updateList() {
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}
