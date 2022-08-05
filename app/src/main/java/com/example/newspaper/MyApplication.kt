package com.example.newspaper

import android.app.Application
import android.content.Context
import com.example.newspaper.data.database.ArticleDatabase

class MyApplication: Application() {

    companion object {

        private lateinit var instance: MyApplication

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ArticleDatabase.initDatabase(this)
    }
}