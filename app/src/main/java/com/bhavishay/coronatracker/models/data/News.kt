package com.bhavishay.coronatracker.models.data

import com.bhavishay.coronatracker.helpers.TimeHelper
import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*

data class NewsApiResponse(
    @Json(name = "status")val status:String,
    @Json(name = "articles")val articles:List<NewsItem>,
    @Json(name = "code")val errorCode:String?,
    @Json(name = "message")val errorMessage:String?,
    @Json(name = "totalResults")val totalResults:Int

)

data class NewsItem(
    @Json(name = "author")val authorName: String?,
    @Json(name = "title")val newsTitle: String?,
    @Json(name = "description")val description: String?,
    @Json(name = "url")val url: String?,
    @Json(name = "urlToImage")val urlToImage: String?,
    @Json(name = "publishedAt")val publishedAt: String?,
    @Json(name = "content")val content: String?,
    @Json(name = "source")val source: ArticleSource
) {
    var timeString:String = ""
    init {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        timeString = TimeHelper.getTimeAgo(formatter.parse(publishedAt!!)?.time!!)!!
    }
}

data class ArticleSource(
    @Json(name = "id")val sourceId: String?,
    @Json(name = "name")val sourceName: String
) {

}

