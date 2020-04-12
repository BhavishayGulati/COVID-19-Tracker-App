package com.bhavishay.coronatracker.repository.database

import androidx.room.*
import com.bhavishay.coronatracker.models.data.WorldTotalStats

@Dao
interface WorldStatsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stats:WorldTotalStats)

    @Update
    fun update(stats:WorldTotalStats)

    @Query("SELECT * from world_stats WHERE id = :id")
    fun get(id:Int = 0):WorldTotalStats?
}