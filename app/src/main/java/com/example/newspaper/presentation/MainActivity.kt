package com.example.newspaper.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.newspaper.R
import com.example.newspaper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val newsFragment = NewsFragment()
    private val profileFragment = ProfileFragment()

    private var _binding: ActivityMainBinding? = null
    private val binding by lazy { _binding!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.mainMenu.itemIconTintList = null

        replaceFragment(newsFragment)

        binding.mainMenu.setOnItemSelectedListener {
            if(it.itemId == R.id.action_home) {
                replaceFragment(newsFragment)
            }
            else {
                replaceFragment(profileFragment)
            }
            return@setOnItemSelectedListener true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFrameLayout, fragment)
        transaction.commit()
    }
}