package com.example.newspaper.presentation.faq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import com.example.newspaper.R
import com.example.newspaper.databinding.ActivityFaqBinding
import com.example.newspaper.databinding.ActivityFavoriteArticleBinding

class FaqActivity : AppCompatActivity() {

    private var _binding: ActivityFaqBinding? = null
    private val binding: ActivityFaqBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFaqBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

    }
}