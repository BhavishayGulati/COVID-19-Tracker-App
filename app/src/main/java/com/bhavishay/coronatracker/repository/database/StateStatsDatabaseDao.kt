package com.bhavishay.coronatracker.repository.database

import androidx.room.*
import com.bhavishay.coronatracker.models.data.State

@Dao
interface StateStatsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stateStats: State)

    @Update
    fun update(stateStats: State)

    @Query("SELECT * from states_stats_table WHERE stateName = :stateName")
    fun getStateStats(stateName:String):State?

    @Query("SELECT * from states_stats_table")
    fun getAllStates():List<State>?
}