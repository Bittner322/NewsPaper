package com.example.newspaper.presentation.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.data.database.models.Article
import com.example.newspaper.databinding.FragmentNewsBinding
import com.example.newspaper.di.ComponentStorage
import com.example.newspaper.di.feature_components.DaggerNewsFragmentComponent
import com.example.newspaper.di.provideRootComponent
import com.example.newspaper.presentation.full_article.FullArticleActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val INTENT_ARTICLE_ID = "articleId"

class NewsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    private val viewModel: NewsViewModel by viewModels { viewModelFactory }

    private val daggerComponentKey = "NewsFragment"

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComponentStorage.provideComponent(daggerComponentKey) {
            DaggerNewsFragmentComponent.factory().create(
                appComponent = ComponentStorage.provideRootComponent()
            )
        }.inject(this)
    }

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

        binding.searchView.setOnQueryTextListener(object: android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                viewModel.loadNewsBySearchQuery(query.toString())
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        lifecycleScope.launchWhenStarted {

            viewModel.newsFlow
                .onEach {
                    adapter.setData(it)
                }
                .launchIn(this)

            viewModel.isProgressBarVisible
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

    override fun onStart() {
        super.onStart()

        viewModel.loadNewsFromNetwork()
    }

    override fun onDetach() {

        if (!requireActivity().isChangingConfigurations) {
            ComponentStorage.clearComponent(daggerComponentKey)
        }

        super.onDetach()
    }

}