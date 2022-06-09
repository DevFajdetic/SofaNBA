package com.example.sofanba.data.database

import androidx.room.*
import com.example.sofanba.data.database.model.FavoriteTeam

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: FavoriteTeam)

    @Delete
    suspend fun deleteTeam(team: FavoriteTeam)

    @Query("SELECT EXISTS (SELECT 1 FROM FavoriteTeam WHERE id=:id)")
    suspend fun isFavorite(id: Int): Boolean

    @Query("SELECT * FROM FavoriteTeam")
    suspend fun getAllFavTeams(): List<FavoriteTeam>

    @Query("DELETE FROM FavoriteTeam")
    suspend fun clearTable()
}