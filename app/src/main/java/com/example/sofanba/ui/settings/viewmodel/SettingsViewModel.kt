package com.example.sofanba.ui.settings.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.data.api.RetrofitInstance
import com.example.sofanba.data.api.model.Games
import com.example.sofanba.data.database.NbaDatabase
import com.example.sofanba.utils.Resource
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    fun clearTables(context: Context) {
        viewModelScope.launch {
            try {
                NbaDatabase.getDatabase(context)?.PlayerDao()?.clearTable()
                NbaDatabase.getDatabase(context)?.TeamDao()?.clearTable()
                Toast.makeText(context, "Cleared successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Error with clearing favorites", Toast.LENGTH_SHORT).show()
            }
        }
    }
}