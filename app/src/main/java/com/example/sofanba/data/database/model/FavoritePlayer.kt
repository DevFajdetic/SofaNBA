package com.example.sofanba.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoritePlayer")
data class FavoritePlayer (
    val first_name: String,
    val height_feet: Int?,
    val height_inches: Int?,
    @PrimaryKey
    val id: Int,
    val last_name: String,
    val position: String,
    val weight_pounds: Int?,
    val index_pos: Int,
    )