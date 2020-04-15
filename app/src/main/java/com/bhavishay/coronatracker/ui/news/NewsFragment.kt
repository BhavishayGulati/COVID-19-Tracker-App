package com.bhavishay.coronatracker.ui.news

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.bhavishay.coronatracker.R
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModel
    private val newsListAdapter = NewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsRecyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

        viewModel.getNews().observe(viewLifecycleOwner, Observer {
           progressBar.visibility = View.GONE
            newsRecyclerView.visibility = View.VISIBLE
            newsListAdapter.submitList(it)
        })

        newsRecyclerView.layoutManager = LinearLayoutManager(context)
        newsRecyclerView.adapter = newsListAdapter
    }



}
