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

private const val INTENT_ARTICLE_ID = "articleId"

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
            onItemClick = ::onItemClick,
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
    }

    private fun onItemClick(article: Article) {
        val toFullArticleIntent = Intent(this, FullArticleActivity::class.java)
        toFullArticleIntent.putExtra(INTENT_ARTICLE_ID, article.articleId)
        startActivity(toFullArticleIntent)
    }

    private fun onToggleCheckClick(article: Article) {
        viewModel.setArticleFavorite(article)
    }

    private fun onToggleNonCheckClick(article: Article) {
        viewModel.setArticleNonFavorite(article)
    }
}