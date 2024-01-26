package com.example.myapplication

import androidx.lifecycle.LiveData

class MatchRepository(private val matchDao: FixtureDAO) {

    fun getAllMatches(): LiveData<List<MatchEntity>> {
        return matchDao.getAllMatches()
    }
    suspend fun insertMatches(matches: List<MatchEntity>) {
        matchDao.insertAll(matches)
    }
}