package com.example.sofanba.data.api.model

data class Player (
    val id: Int,
    val first_name: String,
    val last_name: String,
    val height_feet: Int?,
    val height_inches: Int?,
    val position: String,
    val team: Team,
    val weight_pounds: Int?
) {
    val formattedFullName: String
        get() = String.format("%s %s", first_name, last_name)
}