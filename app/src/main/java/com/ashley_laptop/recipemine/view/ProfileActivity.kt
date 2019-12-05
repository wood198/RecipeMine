package com.ashley_laptop.recipemine.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ashley_laptop.recipemine.R
import com.ashley_laptop.recipemine.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileViewModel: ProfileViewModel
    private var user: String = ""
    private var isVeggies: Boolean = false
    private var isVegans: Boolean = false
    private var isGlutens: Boolean = false
    private var isDiets: Boolean = false


    private fun getUserName() = intent.extras?.get("username").toString().trim()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("linz3", "username: ${getUserName()}")

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
//        profileViewModel.getUserProfilePass(getUserName()).observe(this,
//            androidx.lifecycle.Observer {updateProfile(it)})


        user = getUserName()
        username.text = "User: ${user.toString()}"

        if(diet.isChecked){
            isDiets = true
            profileViewModel.setUserProfileDiet(getUserName(), isDiets)
        }

        if(veggie.isChecked){
            isVeggies = true
            profileViewModel.setUserProfileVeggie(getUserName(), isVeggies)
        }

        if(vegan.isChecked){
            isVegans = true
            profileViewModel.setUserProfileVegan(getUserName(), isVegans)
        }

        if(gluten.isChecked){
            isGlutens = true
            profileViewModel.setUserProfileGluten(getUserName(), isGlutens)
        }
    }
    //I am going to need to refactor all the stuff I just did in Main. Profile should be updated
    //in this activity. There will need to be an edit button

}