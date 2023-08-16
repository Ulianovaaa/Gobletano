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
        private const val USERNAME = "you"
        private val Level = Int
        private val Levels_comp = 0
        private val SOUND = true

        fun getLv(): Int{
            return sharedPref!!.getInt("Level", 0)
        }

        fun setLv(l: Int){
            sharedPref?.edit()
                ?.putInt("Level", l)
                ?.apply()
        }

        fun getLvs(): Int{
            return sharedPref!!.getInt("Levels_comp", 0)
        }

        fun setLvs(l: Int){
            sharedPref?.edit()
                ?.putInt("Levels_comp", l)
                ?.apply()
        }
        fun getUn(): String? {
            return sharedPref!!.getString(USERNAME, "")
        }

        fun setUn(s: String){
            sharedPref?.edit()
                ?.putString(USERNAME, s)
                ?.apply()
        }

        fun getSound(): Boolean {
            return sharedPref!!.getBoolean("SOUND", true)
        }

        fun setSound(s: Boolean){
            sharedPref?.edit()
                ?.putBoolean("SOUND", s)
                ?.apply()
        }
    }
}