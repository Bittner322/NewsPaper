package com.example.newspaper.presentation.full_article

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.newspaper.MyApplication
import com.example.newspaper.databinding.ActivityFullArticleBinding
import com.example.newspaper.di.DaggerFullArticleActivityComponent
import dagger.Component.Factory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val INTENT_ARTICLE_ID = "articleId"

class FullArticleActivity : AppCompatActivity() {

    private var _binding: ActivityFullArticleBinding? = null
    private val binding: ActivityFullArticleBinding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: FullArticleViewModelFactory

    private var daggerComponentKey = "FullArticleActivity"

    private val viewModel: FullArticleViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyApplication.provideComponent(daggerComponentKey) {
            return@provideComponent DaggerFullArticleActivityComponent.factory()
                .create(
                    appComponent = MyApplication.appComponent,
                    url = intent.getStringExtra(INTENT_ARTICLE_ID).orEmpty()
                )
        }.inject(this)

        _binding = ActivityFullArticleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launchWhenStarted {
            viewModel.titleStateFlow
                .onEach { binding.fullArticleTitleTextView.text = it }
                .launchIn(this)

            viewModel.authorStateFlow
                .onEach { binding.fullArticleAuthorTextView.text = it }
                .launchIn(this)

            viewModel.dateStateFlow
                .onEach { binding.fullArticleDateTextView.text = it }
                .launchIn(this)

            viewModel.descriptionStateFlow
                .onEach { binding.fullArticleDescriptionTextView.text = it }
                .launchIn(this)

            viewModel.urlToImageStateFlow
                .onEach { binding.backDropImageView.load(it) }
                .launchIn(this)
        }
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            MyApplication.clearComponent(daggerComponentKey)
        }
        super.onDestroy()
    }

}