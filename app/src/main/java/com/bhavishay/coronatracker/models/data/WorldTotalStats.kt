package com.bhavishay.coronatracker.models.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "world_stats")
data class WorldTotalStats(

    @PrimaryKey()
    val id: Int = 0,

    @Json(name = "total_cases")
    @ColumnInfo(name = "total_cases")
    val totalCases: String,

    @Json(name = "total_deaths")
    @ColumnInfo(name = "total_deaths")
    val totalDeaths: String,

    @Json(name = "total_recovered")
    @ColumnInfo(name = "total_recovered")
    val totalRecovered: String,

    @Json(name = "new_cases")
    @ColumnInfo(name = "new_cases")
    val newCases: String,

    @Json(name = "new_deaths")
    @ColumnInfo(name = "new_deaths")
    val newDeaths: String,

    @Json(name = "statistic_taken_at")
    @ColumnInfo(name = "statistic_taken_at")
    val statsTakenAt: String,

    @ColumnInfo(name = "last_network_call_time")
    var lastNetworkCallTime: String = ""
) {
}


data class WorldStatsResponse(

    @Json(name = "countries_stat")
    val countriesStats: List<Country>,

    @Json(name = "world_total")
    val worldTotalStats: WorldTotalStats
) {}