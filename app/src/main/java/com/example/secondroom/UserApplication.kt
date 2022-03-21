package com.example.secondroom

import android.app.Application

import com.example.secondroom.db.UserDatabase
import com.example.secondroom.db.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class UsersApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { UserDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { UserRepository(database.userDao()) }
}