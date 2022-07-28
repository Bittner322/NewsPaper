package com.example.newspaper.presentation.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.newspaper.R
import com.example.newspaper.databinding.ActivityMainBinding
import com.example.newspaper.presentation.news.NewsFragment
import com.example.newspaper.presentation.profile.ProfileFragment
import com.example.newspaper.presentation.profile.ProfileViewModel

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null
    private val binding :ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val newsFragment = NewsFragment()
        val profileFragment = ProfileFragment()

        replaceFragment(newsFragment)

        binding.mainMenu.setOnItemSelectedListener { menuItem ->

            when(menuItem.itemId) {
                R.id.action_home -> replaceFragment(newsFragment)
                R.id.action_profile -> replaceFragment(profileFragment)
            }

            return@setOnItemSelectedListener true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.mainFrameLayout, fragment)
        }
    }
}