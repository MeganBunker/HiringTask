package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FixtureDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<MatchEntity>)

    @Query("SELECT * FROM matches")
    fun getAllMatches(): LiveData<List<MatchEntity>>
}
