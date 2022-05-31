package com.example.sofanba.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sofanba.data.model.Player

@Database(entities = [Player::class], version = 1, exportSchema = false)
abstract class NbaDatabase: RoomDatabase() {
    abstract fun PlayerDao(): PlayerDao
    abstract fun TeamDao(): TeamDao

    companion object {
        private var instance: NbaDatabase? = null

        fun getDatabase(context: Context): NbaDatabase? {
            if(instance == null) {
                instance = buildDatabase(context)
            }
            return instance
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                NbaDatabase::class.java,
                "FavoritesDatabase"
            ).build()
    }

}