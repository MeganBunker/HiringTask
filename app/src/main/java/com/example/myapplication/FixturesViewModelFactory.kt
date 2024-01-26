package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FixturesViewModelFactory(private val repository: MatchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FixturesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FixturesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}