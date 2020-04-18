package com.bhavishay.coronatracker.ui.news

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.bhavishay.coronatracker.models.data.NewsItem
import com.bhavishay.coronatracker.repository.network.NewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.lang.Exception

class NewsDataSource(private val scope:CoroutineScope,
                     private val dateFrom:String
) : PageKeyedDataSource<Long, NewsItem>() {

    var newsApiService = NewsApi.retrofitService
    val newsSearchKey = "covid India"

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, NewsItem>
    ) {
        scope.launch(Dispatchers.IO) {
            try {
                val response = newsApiService.getNews(
                    keyword = newsSearchKey,
                    dateFrom = dateFrom,
                    pageSize = params.requestedLoadSize,
                    pageNo = 1
                    )
                when{
                    response.isSuccessful -> {
                        val newsList = response.body()?.articles
                        callback.onResult(newsList?: listOf(),null,2)
                    }
                }
            }catch (exception:Exception){
                Log.e("newsList","Error loading news ${exception.localizedMessage}")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, NewsItem>) {
        Log.d("newsList","Loading page ${params.key} count ${params.requestedLoadSize}")

        scope.launch(Dispatchers.IO) {
            try {
                val response = newsApiService.getNews(
                    keyword = newsSearchKey,
                    dateFrom = dateFrom,
                    pageSize = params.requestedLoadSize,
                    pageNo = params.key.toInt()
                )

                when{
                    response.isSuccessful -> {
                        val nextKey = if(params.key.toInt() < (response.body()?.totalResults?.div(
                                params.requestedLoadSize.toInt()
                            ))!!) params.key+1 else null
                        val newsList = response.body()?.articles
                        callback.onResult(newsList?: listOf(),nextKey)
                    }

                }
            }catch (exception:Exception){
                Log.e("newsList","Error loading news ${exception.localizedMessage}")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, NewsItem>) {
        TODO("Not yet implemented")
    }
}