package com.bhavishay.coronatracker.models.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "states_stats_table")
data class State(

    @Json(name = "state")
    @PrimaryKey()
    val stateName: String,

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
    val recoveredCases: String,


    @Json(name = "statecode")
    @ColumnInfo(name = "statecode")
    val stateCode: String
)