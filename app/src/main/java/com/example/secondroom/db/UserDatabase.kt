package com.example.secondroom.db

import androidx.room.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

import com.squareup.moshi.Types
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
@ProvidedTypeConverter
class RoomConverter  @Inject constructor(val moshi:Moshi){

    private fun <T> getAdapter(clazz: Class<T>): JsonAdapter<List<T>> {
        val listType = Types.newParameterizedType(List::class.java, clazz)
        return moshi.adapter(listType)
    }
    @TypeConverter
    fun fromUserList(data: List<User>): String {
        return getAdapter(User::class.java).toJson(data)
    }
    @TypeConverter
    fun toMediaList(json: String): List<User>? {
        return getAdapter(User::class.java).fromJson(json)
    }
}

@Database(
    version = 1,
    entities = [User::class],

)
@TypeConverters(RoomConverter::class)
        abstract class UserDatabase : RoomDatabase(){
            abstract fun userDao(): UserDao
        }
