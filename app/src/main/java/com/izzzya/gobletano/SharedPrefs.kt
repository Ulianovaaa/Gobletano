package com.izzzya.gobletano

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {
    init {
        sharedPref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

    }
    companion object {
        private var sharedPref: SharedPreferences? = null
        private const val PREFERENCES = "prefs"
        private val Level = Int

        fun getLv(): Int{
            return sharedPref!!.getInt("Level", 0)
        }

        fun setLv(l: Int){
            sharedPref?.edit()
                ?.putInt("Level", l)
                ?.apply()
        }
    }
}