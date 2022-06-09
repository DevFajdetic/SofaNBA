package com.example.sofanba.data.database

import androidx.room.*
import com.example.sofanba.data.api.model.Player
import com.example.sofanba.data.database.model.FavoritePlayer

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: FavoritePlayer)

    @Delete
    suspend fun deletePlayer(player: FavoritePlayer)

    @Query("SELECT EXISTS (SELECT 1 FROM FavoritePlayer WHERE id=:id)")
    suspend fun isFavorite(id: Int): Boolean

    @Query("SELECT * FROM FavoritePlayer")
    suspend fun getAllFavPlayers(): List<FavoritePlayer>

    @Query("DELETE FROM FavoritePlayer")
    suspend fun clearTable()
}