package com.example.sofanba.ui.explore.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.data.api.RetrofitInstance
import com.example.sofanba.data.database.NbaDatabase
import com.example.sofanba.data.api.model.Players
import com.example.sofanba.data.api.model.Teams
import com.example.sofanba.data.database.model.FavoritePlayer
import com.example.sofanba.data.database.model.FavoriteTeam
import com.example.sofanba.utils.Resource
import kotlinx.coroutines.launch

class ExploreViewModel() : ViewModel() {

    val allPlayersReponse = MutableLiveData<Resource<Players>>()
    val allTeamsResponse = MutableLiveData<Resource<Teams>>()

    fun getAllPlayers(page: Int? = null, per_page: Int? = null, search: String? = null) {
        viewModelScope.launch {
            allPlayersReponse.postValue(Resource.loading(null))
            try {
                val response = RetrofitInstance.api.getAllPlayers(page, per_page, search)
                allPlayersReponse.postValue(Resource.success(response))
            } catch (e: Exception) {
                allPlayersReponse.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getAllTeams(page: Int? = null, per_page: Int? = null) {
        viewModelScope.launch {
            allPlayersReponse.postValue(Resource.loading(null))
            try {
                val response = RetrofitInstance.api.getAllTeams(page, per_page)
                allTeamsResponse.postValue(Resource.success(response))
            } catch (e: Exception) {
                allTeamsResponse.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun insertFavoritePlayer(context: Context, player: FavoritePlayer) {
        viewModelScope.launch {
            NbaDatabase.getDatabase(context)?.PlayerDao()?.insertPlayer(player)
        }
    }

    fun checkFavoritePlayer(context: Context, player: FavoritePlayer): Boolean? {
        var response = false
        viewModelScope.launch {
            response = NbaDatabase.getDatabase(context)?.PlayerDao()?.isFavorite(player.id) == true
        }
        return response
    }

    fun removeFavoritePlayer(context: Context, player: FavoritePlayer) {
        viewModelScope.launch {
            NbaDatabase.getDatabase(context)?.PlayerDao()?.deletePlayer(player)
        }
    }

    fun insertFavoriteTeam(context: Context, team: FavoriteTeam) {
        viewModelScope.launch {
            NbaDatabase.getDatabase(context)?.TeamDao()?.insertTeam(team)
        }
    }

    fun checkFavoriteTeam(context: Context, team: FavoriteTeam): Boolean? {
        var response = false
        viewModelScope.launch {
            response = NbaDatabase.getDatabase(context)?.TeamDao()?.isFavorite(team.id) == true
        }
        return response
    }

    fun removeFavoriteTeam(context: Context, team: FavoriteTeam) {
        viewModelScope.launch {
            NbaDatabase.getDatabase(context)?.TeamDao()?.deleteTeam(team)
        }
    }
}
