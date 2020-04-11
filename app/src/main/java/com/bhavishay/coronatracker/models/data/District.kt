package com.bhavishay.coronatracker.models.data

import com.squareup.moshi.Json

data class District(
   @Json(name = "confirmed") val confirmedCases:String
) {
}