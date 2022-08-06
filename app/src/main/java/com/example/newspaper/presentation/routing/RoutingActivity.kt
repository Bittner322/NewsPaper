package com.example.newspaper.presentation.routing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newspaper.presentation.main_activity.MainActivity

class RoutingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toMainActivityIntent = Intent(this, MainActivity::class.java)
        startActivity(toMainActivityIntent)
        finish()
    }
}