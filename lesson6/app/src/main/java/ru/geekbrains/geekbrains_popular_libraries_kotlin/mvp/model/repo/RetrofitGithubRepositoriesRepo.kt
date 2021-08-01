package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val db: Database,
    val networkStatus: INetworkStatus
) : IGithubRepositoriesRepo {

    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getRepositories(user.reposUrl).flatMap { repositories ->
                    Single.fromCallable {
                        val roomUser = db.userDao.findByLogin(user.login) ?: throw RuntimeException(
                            "No such user"
                        )
                        val roomRepositories = repositories.map { repository ->
                            RoomGithubRepository(
                                repository.id,
                                repository.name,
                                repository.forksCount,
                                roomUser.id,
                            )
                        }
                        db.repositoryDao.insert(roomRepositories)
                        repositories
                    }
                }
            } else {
                Single.fromCallable {
                    val roomUser = db.userDao.findByLogin(user.login) ?: throw RuntimeException(
                        "No such user"
                    )
                    db.repositoryDao.findForUser(roomUser.id).map { roomRepository ->
                        GithubRepository(
                            roomRepository.id,
                            roomRepository.name,
                            roomRepository.forksCount,
                        )
                    }
                }
            }
        }.subscribeOn(Schedulers.io())
}