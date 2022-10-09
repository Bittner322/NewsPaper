package com.example.newspaper.presentation.history

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.data.database.models.Article
import com.example.newspaper.databinding.ActivityHistoryBinding
import com.example.newspaper.di.ComponentStorage
import com.example.newspaper.di.feature_components.DaggerHistoryActivityComponent
import com.example.newspaper.di.provideRootComponent
import com.example.newspaper.presentation.full_article.FullArticleActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val INTENT_ARTICLE_ID = "articleId"

class HistoryActivity : AppCompatActivity() {

    private var _binding: ActivityHistoryBinding? = null
    private val binding: ActivityHistoryBinding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: HistoryActivityModelFactory

    private val viewModel: HistoryViewModel by viewModels { viewModelFactory }

    private val daggerComponentKey = "HistoryActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComponentStorage.provideComponent(daggerComponentKey) {
            DaggerHistoryActivityComponent.factory().create(
                appComponent = ComponentStorage.provideRootComponent()
            )
        }.inject(this)

        _binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = HistoryRecyclerAdapter(onItemClick = ::onHistoryClick)
        binding.historyRecyclerView.adapter = adapter

        binding.clearHistoryButton.setOnClickListener {
            viewModel.onClearHistoryClick()
        }

        binding.historyActivityBackButton.setOnClickListener {
            finish()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.historyArticles
                .onEach {
                    adapter.setData(it)
                }
                .launchIn(this)
        }
    }

    private fun onHistoryClick(article: Article) {
        val toFullArticleActivityIntent = Intent(this, FullArticleActivity::class.java)
        toFullArticleActivityIntent.putExtra(INTENT_ARTICLE_ID, article.url)
        startActivity(toFullArticleActivityIntent)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            ComponentStorage.clearComponent(daggerComponentKey)
        }
        super.onDestroy()
    }
}