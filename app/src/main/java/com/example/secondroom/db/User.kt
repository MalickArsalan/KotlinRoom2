package com.example.secondroom.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user")
@Parcelize
@JsonClass(generateAdapter = true)
data class User (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val firstName :String,
    val middleName :String,
    val lastName :String,
    val age:Int,
    val gender: String
    ): Parcelable