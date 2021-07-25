package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.UserRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list.IRepoListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.RepoItemView

class UserPresenter(
    private val router: Router,
    private val user: GithubUser,
    private val usersRepo: IGithubUsersRepo,
    private val uiScheduler: Scheduler
) : MvpPresenter<UserView>() {

    class RepoListPresenter : IRepoListPresenter {

        val repos = mutableListOf<UserRepository>()

        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun bindView(view: RepoItemView) {
            view.setName(repos[view.getPos()].name)
        }

        override fun getCount() = repos.size
    }

    val repoListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        val reposUrl = user.reposUrl ?: return
        usersRepo.getUserRepositories(reposUrl).observeOn(uiScheduler).subscribe({
            repoListPresenter.repos.clear()
            repoListPresenter.repos.addAll(it)
            viewState.updateList()
        }, {
            println("Error: ${it.message}")
        })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
