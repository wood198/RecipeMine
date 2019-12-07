package com.ashley_laptop.recipemine

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import me.ibrahimsn.library.LiveSharedPreferences

class ProfileRepository(context: Context) {

    //create the sharedpreferences
    private val preferences: SharedPreferences
    private val liveSharedPreferences: LiveSharedPreferences

    //initializing sharedpreferences in private mode
    init {
        preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        liveSharedPreferences = LiveSharedPreferences(preferences)
    }

    //function to set the user count in storage
    fun setUserProfilePass(name: String, password: String) {
        preferences.edit().putString("${name}Pass", password).apply()
    }

    fun setUserProfileDairy(name: String, dairy: Boolean) {
        preferences.edit().putBoolean("${name}Diet", dairy).apply()
    }

    fun setUserProfileVeggie(name: String, veggie: Boolean) {
        preferences.edit().putBoolean("${name}Veggie", veggie).apply()
    }

    fun setUserProfileVegan(name: String, vegan: Boolean) {
        preferences.edit().putBoolean("${name}Vegan", vegan).apply()
    }

    fun setUserProfileGluten(name: String, gluten: Boolean) {
        preferences.edit().putBoolean("${name}Gluten", gluten).apply()
    }

    //function to set the user name in storage
    fun getUserProfilePass(name: String): LiveData<String> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name), "")) {it[name]}

    fun getUserProfileDiet(name: String): LiveData<Boolean> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name), false)) {it[name]}

    fun getUserProfileVeggie(name: String): LiveData<Boolean> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name), false)) {it[name]}

    fun getUserProfileVegan(name: String): LiveData<Boolean> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name), false)) {it[name]}

    fun getUserProfileGluten(name: String): LiveData<Boolean> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name), false)) {it[name]}

    //create the constant value for storage
    companion object {
        private const val PREFS = "profile"
    }
}