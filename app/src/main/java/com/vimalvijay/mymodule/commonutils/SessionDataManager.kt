package com.vimalvijay.mymodule.commonutils


import android.content.SharedPreferences
import javax.inject.Inject

class SessionDataManager @Inject constructor(var sharedPreferences: SharedPreferences) {

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    var authToken: String
        get() = sharedPreferences.getString(::authToken.name, "")!!
        set(value) {sharedPreferences.edit().putString(::authToken.name, value).apply() }

}