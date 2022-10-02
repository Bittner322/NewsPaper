package com.example.newspaper

import android.app.Application
import android.content.Context
import com.example.newspaper.data.database.databases.ArticleDatabase
import com.example.newspaper.di.ComponentStorage
import com.example.newspaper.di.DaggerAppComponent
import com.example.newspaper.di.initRootComponent


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
        ComponentStorage.initRootComponent { DaggerAppComponent.create() }
        ArticleDatabase.initDatabase(this)
    }
}