package com.example.secondroom.db.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.secondroom.db.User
import com.example.secondroom.db.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    val  allUsers: LiveData<List<User>> = userDao.loadUser()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }
}