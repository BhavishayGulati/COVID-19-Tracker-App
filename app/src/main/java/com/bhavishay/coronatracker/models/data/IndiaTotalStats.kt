package com.bhavishay.coronatracker.models.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "india_stats")
data class IndiaTotalStats(
    @PrimaryKey()
    val id:Int =0,

    @Json(name = "active")
    @ColumnInfo(name = "active")
    val activeCases: String,

    @Json(name = "confirmed")
    @ColumnInfo(name = "confirmed")
    val confirmedCases: String,

    @Json(name = "deaths")
    @ColumnInfo(name = "deaths")
    val deaths: String,

    @Json(name = "deltaconfirmed")
    @ColumnInfo(name = "deltaconfirmed")
    val deltaConfirmed: String,

    @Json(name = "deltadeaths")
    @ColumnInfo(name = "deltadeaths")
    val deltaDeaths: String,

    @Json(name = "deltarecovered")
    @ColumnInfo(name = "deltarecovered")
    val deltaRecovered: String,

    @Json(name = "lastupdatedtime")
    @ColumnInfo(name = "lastupdatedtime")
    val lastUpdatedTime: String,

    @Json(name = "recovered")
    @ColumnInfo(name = "recovered")
    val recoveredCases: String
) {

}

data class IndiaStatsResponse(
    @Json(name = "total_values") val indiaTotalStat: IndiaTotalStats,
    @Json(name = "state_wise") val states: Map<String, State>
)