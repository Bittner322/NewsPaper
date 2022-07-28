package com.example.newspaper.presentation.history

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.data.database.Article
import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.databinding.ActivityHistoryBinding
import com.example.newspaper.presentation.full_article.FullArticleActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {

    private var _binding: ActivityHistoryBinding? = null
    private val binding: ActivityHistoryBinding
        get() = _binding!!

    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = HistoryRecyclerAdapter(onItemClick = ::onHistoryClick)
        binding.historyRecyclerView.adapter = adapter

        binding.clearHistoryButton.setOnClickListener {
            viewModel.onClearHistoryClick()
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
        toFullArticleActivityIntent.putExtra("articleId", article.articleId)
        startActivity(toFullArticleActivityIntent)
    }

    override fun onDestroy() {

        _binding = null

        super.onDestroy()
    }

}