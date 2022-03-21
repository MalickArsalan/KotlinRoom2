package com.example.secondroom.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_table")
//@Parcelize
//@JsonClass(generateAdapter = true)
data class User (

    val firstName :String,
    val middleName :String,
    val lastName :String,
    val fatherName:String,
    val gender: String
    )
{

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}