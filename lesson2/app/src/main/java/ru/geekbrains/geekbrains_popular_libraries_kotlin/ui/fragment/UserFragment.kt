package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.R
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentUserBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.UserPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackButtonListener

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), BackButtonListener, UserView {

    private val presenter by moxyPresenter { UserPresenter(App.instance.router) }
    private var vb: FragmentUserBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vb = FragmentUserBinding.bind(view)
    }

    override fun backPressed() = presenter.backClick()

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) ?: return
        vb?.tvUser?.text = user.toString()
    }

    companion object {
        private const val USER_ARG = "USER"
        fun getInstance(user: GithubUser) =
            UserFragment().apply {
                arguments = bundleOf(USER_ARG to user)
            }
    }
}
