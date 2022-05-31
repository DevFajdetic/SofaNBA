package com.example.sofanba.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "FavoritePlayer")
data class Player(
    val first_name: String,
    val height_feet: Any?,
    val height_inches: Any?,
    @PrimaryKey
    val id: Int,
    val last_name: String,
    val position: String,
    @Ignore
    val team: Team,
    val weight_pounds: Any?
) {
    val formattedFullName: String
        get() = String.format("%s %s", first_name, last_name)
}