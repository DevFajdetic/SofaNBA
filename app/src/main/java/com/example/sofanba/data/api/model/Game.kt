package com.example.sofanba.data.api.model

data class Game(
    val date: String,
    val home_team: Team,
    val home_team_score: Int,
    val id: Int,
    val period: Int,
    val postseason: Boolean,
    val season: Int,
    val status: String,
    val time: String,
    val visitor_team: Team,
    val visitor_team_score: Int
)