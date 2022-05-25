package com.example.sofanba.ui.explore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.data.api.RetrofitInstance
import com.example.sofanba.data.model.Players
import com.example.sofanba.data.model.Teams
import kotlinx.coroutines.launch

class ExploreViewModel(): ViewModel() {

    val allPlayersReponse = MutableLiveData<Players>()
    val allTeamsResponse = MutableLiveData<Teams>()

    fun getAllPlayers(page: Int? = null, per_page: Int? = null, search: String? = null) {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getAllPlayers(page, per_page, search)
            allPlayersReponse.value = response
        }
    }

    fun getAllTeams(page: Int? = null, per_page: Int? = null) {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getAllTeams(page, per_page)
            allTeamsResponse.value = response
        }
    }

}