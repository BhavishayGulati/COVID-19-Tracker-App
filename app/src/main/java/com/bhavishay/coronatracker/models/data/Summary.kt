package com.bhavishay.coronatracker.models.data

import androidx.room.ColumnInfo
import com.google.firebase.database.IgnoreExtraProperties
import com.squareup.moshi.Json

@IgnoreExtraProperties
data class Summary(

    @Json(name = "total_recovered")
    @ColumnInfo(name = "total_recovered")
    var cured: Float = 0f,

    @Json(name = "total_deaths")
    @ColumnInfo(name = "total_deaths")
    var dead: Float = 0f,

    @Json(name = "total_cases")
    @ColumnInfo(name = "total_cases")
    var confirmed: Float = 0f,

    @Json(name = "statistic_taken_at")
    @ColumnInfo(name = "statistic_taken_at")
    var updated_at: String = ""
)