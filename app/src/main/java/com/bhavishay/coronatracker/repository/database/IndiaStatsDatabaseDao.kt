package com.bhavishay.coronatracker.repository.database

import androidx.room.*
import com.bhavishay.coronatracker.models.data.IndiaTotalStats

@Dao
interface IndiaStatsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stats:IndiaTotalStats)

    @Query("SELECT * from india_stats WHERE id =:id")
    fun get(id:Int = 0):IndiaTotalStats?
}