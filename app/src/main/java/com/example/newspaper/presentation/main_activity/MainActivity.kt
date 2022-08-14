package com.example.newspaper.presentation.main_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import com.example.newspaper.R
import com.example.newspaper.databinding.ActivityMainBinding
import com.example.newspaper.presentation.news.NewsFragment
import com.example.newspaper.presentation.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

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

        supportFragmentManager.commit {
            add(R.id.mainFrameLayout, newsFragment)
            add(R.id.mainFrameLayout, profileFragment)
            show(newsFragment)
            hide(profileFragment)
        }

        binding.mainMenu.setOnItemSelectedListener { menuItem ->

            when(menuItem.itemId) {
                R.id.action_home ->
                    supportFragmentManager.commit {
                        show(newsFragment)
                        hide(profileFragment)
                    }
                R.id.action_profile ->
                    supportFragmentManager.commit {
                        show(profileFragment)
                        hide(newsFragment)
                    }
            }
            return@setOnItemSelectedListener true
        }
    }
}