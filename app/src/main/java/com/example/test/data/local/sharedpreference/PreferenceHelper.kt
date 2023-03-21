package com.example.test.data.local.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Singleton

@Singleton
class PreferencesHelper(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)


}