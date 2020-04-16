package com.bhavishay.coronatracker.models.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

@IgnoreExtraProperties
@Entity(tableName = "country_stats_table")
data class Country(

    @Json(name = "country_name")
    @PrimaryKey()
    val countryName:String,

    @Json(name = "cases")
    @ColumnInfo(name = "cases")
    val cases:String,

    @Json(name = "deaths")
    @ColumnInfo(name = "deaths")
    val deaths:String,

    @Json(name = "region")
    @ColumnInfo(name = "region")
    val region:String,

    @Json(name = "total_recovered")
    @ColumnInfo(name = "total_recovered")
    val totalRecovered:String,

    @Json(name = "new_deaths")
    @ColumnInfo(name = "new_deaths")
    val newDeaths:String,

    @Json(name = "new_cases")
    @ColumnInfo(name = "new_cases")
    val newCases:String,

    @Json(name = "serious_critical")
    @ColumnInfo(name = "serious_critical")
    val seriousCritical:String,

    @Json(name = "active_cases")
    @ColumnInfo(name = "active_cases")
    val activeCases:String,

    @Json(name = "total_cases_per_1m_population")
    @ColumnInfo(name = "total_cases_per_1m_population")
    val totalCasesPerMillion :String
) {
}

