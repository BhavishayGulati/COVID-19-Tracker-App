package com.bhavishay.coronatracker.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bhavishay.coronatracker.models.data.NewsItem
import java.text.SimpleDateFormat
import java.util.*

class NewsViewModel : ViewModel() {

    var newsListLiveData : LiveData<PagedList<NewsItem>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()

        newsListLiveData = initializedPagedListBuilder(config).build()
    }

    fun getNews():LiveData<PagedList<NewsItem>> = newsListLiveData

    private fun initializedPagedListBuilder(config:PagedList.Config):
            LivePagedListBuilder<Long,NewsItem>{
        val dataSourceFactory = object : DataSource.Factory<Long,NewsItem>(){
            override fun create(): DataSource<Long, NewsItem> {
                val today = Date()
                val formatter = SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH)
                return NewsDataSource(viewModelScope,formatter.format(today))
            }

        }
        return LivePagedListBuilder<Long,NewsItem>(dataSourceFactory,config)
    }

}
