package com.ashley_laptop.recipemine.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ashley_laptop.recipemine.ProfileRepository


class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProfileRepository(application.applicationContext)

    fun getUserProfilePass(name: String, pass: String) = repository.getUserProfile(name)

    fun setUserProfileDiet(name: String, diet: Boolean) = repository.setUserProfileDiet(name,diet)

    fun setUserProfileVeggie(name: String, veggie: Boolean) = repository.setUserProfileVeggie(name,veggie)

    fun setUserProfileVegan(name: String, vegan: Boolean) = repository.setUserProfileVegan(name,vegan)

    fun setUserProfileGluten(name: String, gluten: Boolean) = repository.setUserProfileGluten(name,gluten)
}