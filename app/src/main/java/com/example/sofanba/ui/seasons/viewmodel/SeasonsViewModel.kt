package com.example.sofanba.ui.seasons.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.data.api.RetrofitInstance
import com.example.sofanba.data.api.model.Games
import com.example.sofanba.utils.Resource
import kotlinx.coroutines.launch

class SeasonsViewModel : ViewModel()  {

    val allGamesResponse = MutableLiveData<Resource<Games>>()

    fun getAllGames(page: Int? = null, per_page: Int? = null, dates: List<String>? = null, seasons: List<Int>? = null, teamIds: List<String>? = null, postSeason: Boolean? = null,  startDate: String? = null, endDate: String? = null) {
        viewModelScope.launch {
            allGamesResponse.postValue(Resource.loading(null))
            try {
                val response = RetrofitInstance.api.getAllGames(page, per_page, dates, seasons, teamIds, postSeason, startDate, endDate)
                allGamesResponse.postValue(Resource.success(response))
            } catch (e: Exception) {
                allGamesResponse.postValue(Resource.error(e.toString(), null))
            }
        }
    }
}