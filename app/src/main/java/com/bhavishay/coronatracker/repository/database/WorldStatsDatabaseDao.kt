package com.bhavishay.coronatracker.repository.database

import androidx.room.*
import com.bhavishay.coronatracker.models.data.WorldTotalStats

@Dao
interface WorldStatsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stats:WorldTotalStats)

    @Query("SELECT * from world_stats WHERE id = :id")
    suspend fun get(id:Int = 0):WorldTotalStats?
}