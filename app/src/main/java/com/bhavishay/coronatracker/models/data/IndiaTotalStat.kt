package com.bhavishay.coronatracker.models.data

import com.squareup.moshi.Json

data class IndiaTotalStat(
    @Json(name = "active") val activeCases: String,
    @Json(name = "confirmed") val confirmedCases: String,
    @Json(name = "deaths") val deaths: String,
    @Json(name = "deltaconfirmed") val deltaConfirmed: String,
    @Json(name = "deltadeaths") val deltaDeaths: String,
    @Json(name = "deltarecovered") val deltaRecovered: String,
    @Json(name = "lastupdatedtime") val lastUpdatedTime: String,
    @Json(name = "recovered") val recoveredCases: String
) {

}

data class IndiaStatsResponse(
    @Json(name = "total_values") val indiaTotalStat: IndiaTotalStat,
    @Json(name = "state_wise") val states: Map<String, State>
)