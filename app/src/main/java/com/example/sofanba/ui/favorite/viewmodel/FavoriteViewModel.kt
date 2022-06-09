package com.example.sofanba.ui.favorite.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.data.database.NbaDatabase
import com.example.sofanba.data.database.model.FavoritePlayer
import com.example.sofanba.data.database.model.FavoriteTeam
import kotlinx.coroutines.launch

class FavoriteViewModel(): ViewModel() {
    val allFavReponsePlayer = MutableLiveData<ArrayList<FavoritePlayer>>()
    val allFavReponseTeam = MutableLiveData<ArrayList<FavoriteTeam>>()

    fun getFavoritePlayers(context: Context) {
        viewModelScope.launch {
            val response = NbaDatabase.getDatabase(context)?.PlayerDao()?.getAllFavPlayers()
            if (response != null) {
                allFavReponsePlayer.value = response as ArrayList<FavoritePlayer>
            } else {
                println("Nije dohvatio sve igrace")
            }
        }
    }

    fun removeFavoritePlayer(context: Context, player: FavoritePlayer) {
        viewModelScope.launch {
            NbaDatabase.getDatabase(context)?.PlayerDao()?.deletePlayer(player)
        }
        getFavoritePlayers(context)
    }

    fun getFavoriteTeams(context: Context) {
        viewModelScope.launch {
            val response = NbaDatabase.getDatabase(context)?.TeamDao()?.getAllFavTeams()
            if (response != null) {
                allFavReponseTeam.value = response as ArrayList<FavoriteTeam>
            } else {
                println("Nije dohvatio sve teams")
            }
        }
    }

    fun removeFavoriteTeam(context: Context, team: FavoriteTeam) {
        viewModelScope.launch {
            NbaDatabase.getDatabase(context)?.TeamDao()?.deleteTeam(team)
        }
        getFavoriteTeams(context)
    }
}