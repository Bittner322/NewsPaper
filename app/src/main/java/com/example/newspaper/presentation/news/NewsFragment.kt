package com.example.newspaper.presentation.news

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.data.database.Article
import com.example.newspaper.databinding.FragmentNewsBinding
import com.example.newspaper.presentation.full_article.FullArticleActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val INTENT_ARTICLE_ID = "articleId"

class NewsFragment : Fragment() {

    private val viewModel: NewsViewModel by viewModels()

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(layoutInflater)
        val view = binding.root

        val adapter = NewsRecyclerAdapter(
            onItemClick = ::onNewsClick,
            onToggleChecked = ::onToggleCheckClick,
            onToggleNonChecked = ::onToggleNonCheckClick,
        )

        binding.newsRecyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {

            viewModel.news
                .onEach {
                    adapter.setData(it)
                }
                .launchIn(this)

            viewModel.isProgressBarVisibleFlow
                .onEach { isVisible ->
                    binding.newsProgressBar.isVisible = isVisible
                }
                .launchIn(this)
        }


        return view
    }

    private fun onToggleCheckClick(article: Article) {
        viewModel.setArticleFavorite(article)
    }

    private fun onToggleNonCheckClick(article: Article) {
        viewModel.setArticleNonFavorite(article)
    }

    private fun onNewsClick(article: Article) {

        viewModel.addArticleToHistory(article)

        val toFullArticleActivityIntent = Intent(requireActivity(), FullArticleActivity::class.java)
        toFullArticleActivityIntent.putExtra(INTENT_ARTICLE_ID, article.url)
        startActivity(toFullArticleActivityIntent)
    }
}