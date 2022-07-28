package com.example.newspaper.presentation.favorite_articles

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.data.database.Article
import com.example.newspaper.databinding.ActivityFavoriteArticleBinding
import com.example.newspaper.presentation.full_article.FullArticleActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FavoriteArticleActivity : AppCompatActivity() {

    private val viewModel: FavoriteArticleViewModel by viewModels()

    private var _binding: ActivityFavoriteArticleBinding? = null
    private val binding: ActivityFavoriteArticleBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFavoriteArticleBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val adapter = FavoriteArticlesAdapter(
            onToggleChecked = ::onToggleCheckClick,
            onToggleNonChecked = ::onToggleNonCheckClick,
        )

        binding.favoriteArticlesRecyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.favoriteArticles
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

    private fun onToggleCheckClick(article: Article) {
        viewModel.setArticleFavorite(article)
    }

    private fun onToggleNonCheckClick(article: Article) {
        viewModel.setArticleNonFavorite(article)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}