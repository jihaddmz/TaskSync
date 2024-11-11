package com.jihaddmz.tasksync.feature_global.helper

import android.content.SharedPreferences
import com.jihaddmz.tasksync.sharedPreferences

object HelperSharedPreference  {
    const val KEY_USERNAME = "username"
    const val KEY_PASSWORD = "password"

    fun save(putContent: SharedPreferences.Editor.() -> Unit) {
        with(sharedPreferences.edit()) {
            putContent()
            apply()
        }
    }

    fun <T> get(getContent: SharedPreferences.() -> T): T {
        with(sharedPreferences) {
          return  getContent()
        }
    }
}