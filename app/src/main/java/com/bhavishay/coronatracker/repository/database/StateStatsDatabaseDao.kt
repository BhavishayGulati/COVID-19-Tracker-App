package com.bhavishay.coronatracker.repository.database

import androidx.room.*
import com.bhavishay.coronatracker.models.data.State

@Dao
interface StateStatsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stateStats: State)

    @Query("SELECT * from states_stats_table WHERE stateName = :stateName")
    suspend fun getStateStats(stateName:String):State?

    @Query("SELECT * from states_stats_table")
    suspend fun getAllStates():List<State>?
}