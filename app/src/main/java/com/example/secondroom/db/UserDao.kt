package com.example.secondroom.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun loadUser(): LiveData<List<User>>

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user: User)

}