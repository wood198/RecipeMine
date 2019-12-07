package com.ashley_laptop.recipemine.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ashley_laptop.recipemine.ProfileRepository


class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProfileRepository(application.applicationContext)

    fun setUserProfilePass(name: String, pass: String) = repository.setUserProfilePass(name, pass)

    fun setUserProfileDairy(name: String, dairy: Boolean) = repository.setUserProfileDairy(name,dairy)

    fun setUserProfileVeggie(name: String, veggie: Boolean) = repository.setUserProfileVeggie(name,veggie)

    fun setUserProfileVegan(name: String, vegan: Boolean) = repository.setUserProfileVegan(name,vegan)

    fun setUserProfileGluten(name: String, gluten: Boolean) = repository.setUserProfileGluten(name,gluten)


    fun getUserProfilePass(name: String) = repository.getUserProfilePass(name)

    fun getUserProfileDairy(name: String) = repository.getUserProfileDiet(name)

    fun getUserProfileVeggie(name: String) = repository.getUserProfileVeggie(name)

    fun getUserProfileVegan(name: String) = repository.getUserProfileVegan(name)

    fun getUserProfileGluten(name: String) = repository.getUserProfileGluten(name)
}