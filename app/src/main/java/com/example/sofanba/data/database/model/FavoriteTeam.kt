package com.example.sofanba.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteTeam")
class FavoriteTeam (
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    val full_name: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val index_pos: Int,
)