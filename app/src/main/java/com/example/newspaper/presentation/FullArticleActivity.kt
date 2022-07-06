package com.example.newspaper.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.newspaper.R
import com.example.newspaper.data.NewsResponse
import com.example.newspaper.databinding.ActivityFullArticleBinding
import com.example.newspaper.databinding.ActivityMainBinding
import com.example.newspaper.databinding.FullActivleCoordLayoutBinding
import com.google.gson.Gson

class FullArticleActivity : AppCompatActivity() {

    private var _binding: FullActivleCoordLayoutBinding? = null
    private val binding by lazy { _binding!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_activle_coord_layout)
        _binding = FullActivleCoordLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val gson = Gson()
        val article = gson.fromJson(intent.getStringExtra("article"), NewsResponse.Articles::class.java)

        binding.fullArticleTitleTextView.text = article.title

        if(article.author != null)
            binding.fullArticleAuthorTextView.text = article.author
        else
            binding.fullArticleAuthorTextView.text = "Unknown author"


        binding.fullArticleDateTextView.text = article.publishedAt.substring(0,10)

        if(article.content.length >= 200)
            binding.fullArticleDescriptionTextView.text = article.content.substring(0, 200)
        else
            binding.fullArticleDescriptionTextView.text = article.content

        if(article.urlToImage != null) {
            Glide
                .with(this)
                .load(article.urlToImage)
                .into(binding.backDropImageView)
        }
        else {
            Glide
                .with(this)
                .load(R.drawable.if_image_null2)
                .into(binding.backDropImageView)
        }
    }
}