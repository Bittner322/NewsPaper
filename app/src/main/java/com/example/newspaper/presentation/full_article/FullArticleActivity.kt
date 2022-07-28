package com.example.newspaper.presentation.full_article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.newspaper.R
import com.example.newspaper.databinding.ActivityFullArticleBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class FullArticleActivity : AppCompatActivity() {

    private var _binding: ActivityFullArticleBinding? = null
    private val binding: ActivityFullArticleBinding
        get() = _binding!!

    private val viewModel: FullArticleViewModel by viewModels {
        FullArticleViewModelFactory(
            articleId = intent.getIntExtra("articleId", 0)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                .onEach {
                    Glide
                        .with(this@FullArticleActivity)
                        .load(it)
                        .into(binding.backDropImageView)
                }
                .launchIn(this)
        }
    }

}