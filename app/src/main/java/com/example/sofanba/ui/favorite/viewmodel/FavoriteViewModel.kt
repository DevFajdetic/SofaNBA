package com.example.sofanba.ui.favorite.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.data.database.NbaDatabase
import com.example.sofanba.data.model.Player
import com.example.sofanba.data.model.Players
import kotlinx.coroutines.launch

class FavoriteViewModel(): ViewModel() {
    val allFavReponse = MutableLiveData<List<Any>>()
    var isFav: Boolean = false

    fun getFavoritePlayers(context: Context) {
        viewModelScope.launch {
            val response = NbaDatabase.getDatabase(context)?.PlayerDao()?.getAllFavPlayers()
            if (response != null) {
                allFavReponse.value = response!!
            }
        }
    }

    fun removeFavoritePlayer(context: Context, player: Player) {
        viewModelScope.launch {
            NbaDatabase.getDatabase(context)?.PlayerDao()?.deletePlayer(player)
        }
    }

    fun checkFavoritePlayer(context: Context, player: Player) {
        viewModelScope.launch {
            val response = NbaDatabase.getDatabase(context)!!.PlayerDao().isFavorite(player.id)
            isFav = response
        }
    }
}