package com.bhavishay.coronatracker.models.data

import com.squareup.moshi.Json

data class Country(
    @Json(name = "country_name")val countryName:String,
    @Json(name = "cases")val cases:String,
    @Json(name = "deaths")val deaths:String,
    @Json(name = "region")val region:String,
    @Json(name = "total_recovered")val totalRecovered:String,
    @Json(name = "new_deaths")val newDeaths:String,
    @Json(name = "new_cases")val newCases:String,
    @Json(name = "serious_critical")val seriousCritical:String,
    @Json(name = "active_cases")val activeCases:String,
    @Json(name = "total_cases_per_1m_population")val totalCasesPerMillion :String
) {
}

