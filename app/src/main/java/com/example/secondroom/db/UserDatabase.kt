package com.example.secondroom.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android.roomwordssample.UserRoomDatabase
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
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
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): UserRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "user"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(UserDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class UserDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.

            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */

    }
}

