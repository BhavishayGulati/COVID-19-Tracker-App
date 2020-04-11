package com.bhavishay.coronatracker.models.data

import com.squareup.moshi.Json

data class State(
    @Json(name = "active") val activeCases: String,
    @Json(name = "confirmed") val confirmedCases: String,
    @Json(name = "deaths") val deaths: String,
    @Json(name = "deltaconfirmed") val deltaConfirmed: String,
    @Json(name = "deltadeaths") val deltaDeaths: String,
    @Json(name = "deltarecovered") val deltaRecovered: String,
    @Json(name = "lastupdatedtime") val lastUpdatedTime: String,
    @Json(name = "recovered") val recoveredCases: String,
    @Json(name = "state") val stateName: String,
    @Json(name = "statecode") val stateCode: String,
    @Json(name = "district") val districts: Map<String, District>? = null
) {
}