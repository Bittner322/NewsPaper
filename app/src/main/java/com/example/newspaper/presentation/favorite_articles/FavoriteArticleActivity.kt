package com.example.newspaper.presentation.favorite_articles

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.MyApplication
import com.example.newspaper.data.database.models.Article
import com.example.newspaper.databinding.ActivityFavoriteArticleBinding
import com.example.newspaper.di.ComponentStorage
import com.example.newspaper.di.feature_components.DaggerFavoriteArticleActivityComponent
import com.example.newspaper.di.provideRootComponent
import com.example.newspaper.presentation.full_article.FullArticleActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val INTENT_ARTICLE_ID = "articleId"

class FavoriteArticleActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: FavoriteArticleViewModelFactory

    private val viewModel: FavoriteArticleViewModel by viewModels { viewModelFactory }

    private val daggerComponentKey = "FavoriteArticleActivity"

    private var _binding: ActivityFavoriteArticleBinding? = null
    private val binding: ActivityFavoriteArticleBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComponentStorage.provideComponent(daggerComponentKey) {
            DaggerFavoriteArticleActivityComponent.factory().create(
                appComponent = ComponentStorage.provideRootComponent()
            )
        }.inject(this)

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

        binding.favoriteActivityBackButton.setOnClickListener {
            finish()
        }
    }

    private fun onItemClick(article: Article) {
        val toFullArticleIntent = Intent(this, FullArticleActivity::class.java)
        toFullArticleIntent.putExtra(INTENT_ARTICLE_ID, article.url)
        startActivity(toFullArticleIntent)
    }

    private fun onToggleCheckClick(article: Article) {
        viewModel.setArticleFavorite(article)
    }

    private fun onToggleNonCheckClick(article: Article) {
        viewModel.setArticleNonFavorite(article)
    }

    override fun onDestroy() {
        
        if (!isChangingConfigurations) {
            ComponentStorage.clearComponent(daggerComponentKey)
        }
        super.onDestroy()
    }
}