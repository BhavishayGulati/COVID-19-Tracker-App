package com.bhavishay.coronatracker.repository.network

import com.bhavishay.coronatracker.models.data.IndiaStatsResponse
import com.bhavishay.coronatracker.models.data.WorldStatsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://corona-virus-world-and-india-data.p.rapidapi.com"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

var httpClient = OkHttpClient.Builder()
    .addInterceptor { chain: Interceptor.Chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header("x-rapidapi-host", "corona-virus-world-and-india-data.p.rapidapi.com")
            .header(
                "x-rapidapi-key", "528b37c11amsh6219efd5ce203a8p15c972jsn9bc9ccc46ab3"
            )
            .build()

        chain.proceed(request)
    }

var client = httpClient.build()


private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .build()

interface StatsApiService {

    @GET("api")
    suspend fun getWorldStats(): Response<WorldStatsResponse>

    @GET("api_india")
    suspend fun getIndiaStats(): Response<IndiaStatsResponse>

//    @GET("country_name")
//    suspend fun getCountryName(): Response<WorldStatsResponse>{
//
//    }

}

object StatsApi {
    val retrofitService =  retrofit.create(StatsApiService::class.java)
}

