package com.example.sofanba.data.api

import com.example.sofanba.data.api.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("players")
    suspend fun getAllPlayers(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("search") search: String?
    ): Players

    @GET("players/{id}")
    suspend fun getSpecificPlayer(
        @Path("id") id: Int
    ): Player

    @GET("http://academy-2022.dev.sofascore.com/api/v1/academy/player-image/player/{id}")
    suspend fun getSpecificPlayerImage(
        @Path("id") id: Int
    ): PlayerImages

    @GET("teams")
    suspend fun getAllTeams(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
    ): Teams

    @GET("teams/{id}")
    suspend fun getSpecificTeam(
        @Path("id") id: Int,
    ): Team

    @GET("games")
    suspend fun getAllGames(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("dates[]") dates: List<String>?,
        @Query("seasons[]") seasons: List<Int>?,
        @Query("team_ids") teamIds: List<String>?,
        @Query("postseason") postSeason: Boolean?,
        @Query("start_date") startDate: String?,
        @Query("end_date") endDate: String?,
    ): Games


}