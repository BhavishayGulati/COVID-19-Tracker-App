package com.bhavishay.coronatracker.repository.network

import com.bhavishay.coronatracker.models.data.NewsApiResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface NewsApiService{

    @GET("v2/everything")
    suspend fun getNews(@Query("q") keyword:String,
                        @Query("from") dateFrom:String,
                        @Query("page")pageNo:Int,
                        @Query("pageSize") pageSize:Int = 20,
                        @Query("apiKey") apiKey:String = "dbfbf476dafd4deebfdb2821466db00b"
    ): Response<NewsApiResponse>

}

object NewsApi{
    val retrofitService = retrofit.create(NewsApiService::class.java)
}