package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.user.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.IUserScopeContainer
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.room.RoomGithubUsersCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.RetrofitGithubUsersRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.user.UserScope
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App

@Module
open class UserModule {

    @Provides
    fun usersCache(database: Database): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }

    @UserScope
    @Provides
    open fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache): IGithubUsersRepo {
        return RetrofitGithubUsersRepo(api, networkStatus, cache)
    }

    @UserScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app
}