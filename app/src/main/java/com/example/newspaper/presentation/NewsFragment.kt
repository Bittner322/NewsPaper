package com.example.newspaper.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.databinding.FragmentNewsBinding
import com.google.gson.Gson
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class NewsFragment : Fragment() {

    private val newsViewModel: NewsViewModel by viewModels()

    private var _binding: FragmentNewsBinding? = null
    private val binding by lazy { _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(layoutInflater)
        val view = binding.root

        val adapter = NewsRecyclerAdapter()

        binding.newsRecyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            newsViewModel.news
                .onEach {
                    adapter.setData(it)
                }
                .launchIn(this)
        }

        adapter.onItemClick = {
            val toFullArticleIntent = Intent(activity, FullArticleActivity::class.java)

            val gson = Gson()
            toFullArticleIntent.putExtra("article", gson.toJson(it))
            startActivity(toFullArticleIntent)
        }

        return view
    }

}