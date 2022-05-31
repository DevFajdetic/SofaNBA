package com.example.sofanba.data.database

import androidx.room.*
import com.example.sofanba.data.model.Player

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)

    @Query("SELECT EXISTS (SELECT 1 FROM FavoritePlayer WHERE id=:id)")
    suspend fun isFavorite(id: Int): Boolean

    @Query("SELECT * FROM FavoritePlayer")
    suspend fun getAllFavPlayers(): List<Player>
}