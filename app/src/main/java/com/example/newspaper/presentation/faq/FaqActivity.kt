package com.example.newspaper.presentation.faq

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newspaper.databinding.ActivityFaqBinding

class FaqActivity : AppCompatActivity() {

    private var _binding: ActivityFaqBinding? = null
    private val binding: ActivityFaqBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFaqBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.faqActivityBackButton.setOnClickListener {
            finish()
        }

    }
}