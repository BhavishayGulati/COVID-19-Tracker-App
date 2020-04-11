package com.bhavishay.coronatracker.models.data

import com.squareup.moshi.Json

data class WorldTotalStats(
    @Json(name = "total_cases")val totalCases:String,
    @Json(name = "total_deaths")val totalDeaths:String,
    @Json(name = "total_recovered")val totalRecovered:String,
    @Json(name = "new_cases")val newCases:String,
    @Json(name = "new_deaths")val newDeaths:String,
    @Json(name = "statistic_taken_at")val statsTakenAt:String
) {
}

data class WorldStatsResponse(
    @Json(name = "countries_stat")val countriesStats:List<Country>,
    @Json(name = "world_total")val worldTotalStats: WorldTotalStats
){}