package com.example.movies

import android.app.Application
import android.content.Context


class App : Application() {

    companion object {
        lateinit var app: App
        fun getAppContext(): Context = app.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }

}