package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.CachedImage
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao.ImageDao
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao.RepositoryDao
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao.UserDao
import java.lang.RuntimeException


@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepository::class,
        CachedImage::class
    ],
    version = 2
)
abstract class Database : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

    companion object {

        private const val DB_NAME = "database.db"
        private var instance: Database? = null

        fun getInstance() = instance ?: throw RuntimeException("Create database with create()")

        fun create(context: Context) {
            if(instance == null){
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}