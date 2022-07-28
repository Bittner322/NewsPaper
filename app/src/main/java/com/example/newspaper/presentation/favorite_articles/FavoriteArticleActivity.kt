package com.example.newspaper.presentation.favorite_articles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.R
import com.example.newspaper.databinding.ActivityFavoriteArticleBinding
import com.example.newspaper.databinding.FragmentNewsBinding
import com.example.newspaper.presentation.full_article.FullArticleActivity
import com.google.gson.Gson
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FavoriteArticleActivity : AppCompatActivity() {

    private val favoriteArticleViewModel: FavoriteArticleViewModel by viewModels()

    private var _binding: ActivityFavoriteArticleBinding? = null
    private val binding: ActivityFavoriteArticleBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFavoriteArticleBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val adapter = FavoriteArticlesAdapter()

        binding.favoriteArticlesRecyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            favoriteArticleViewModel.favoriteArticles
                .onEach {
                    adapter.setData(it)
                }
                .launchIn(this)
        }

        adapter.onItemClick = {
            val toFullArticleIntent = Intent(this, FullArticleActivity::class.java)
            toFullArticleIntent.putExtra("articleId", it.articleId)
            startActivity(toFullArticleIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}