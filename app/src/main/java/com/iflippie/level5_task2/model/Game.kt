package com.iflippie.level5_task2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "gameTable")
@Parcelize
data class Game(
    var title: String,
    var platforms: String,
    var releaseDay: Int,
    var releaseMonth: Int,
    var releaseYear: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
): Parcelable {
    override fun toString(): String {
        return "$title available on $platforms\nrelease date: $releaseDay/$releaseMonth/$releaseYear"
    }
}