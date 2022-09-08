package com.example.newspaper

import android.app.Application
import android.content.Context
import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.di.AppComponent
import com.example.newspaper.di.DaggerAppComponent

class MyApplication: Application() {

    companion object {

        private lateinit var instance: MyApplication
        lateinit var appComponent: AppComponent
            private set

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.create()
        ArticleDatabase.initDatabase(this)
    }
}