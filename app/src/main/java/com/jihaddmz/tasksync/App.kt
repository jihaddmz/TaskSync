package com.jihaddmz.tasksync

import android.app.Application
import com.jihaddmz.simplified_requests.SimplifiedRequests
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        SimplifiedRequests.setUpApi("http://192.168.1.101:8080/")

    }
}