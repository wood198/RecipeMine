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
    private var isDairys: Boolean = false


    private fun getUserName() = intent.extras?.get("username").toString().trim()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        profileViewModel.getUserProfileDairy("${getUserName()}Dairy").observe(this,
            androidx.lifecycle.Observer {updateProfileDairy(it)})
        profileViewModel.getUserProfileVeggie("${getUserName()}Veggie").observe(this,
            androidx.lifecycle.Observer {updateProfileVeggie(it)})
        profileViewModel.getUserProfileVegan("${getUserName()}Vegan").observe(this,
            androidx.lifecycle.Observer {updateProfileVegan(it)})
        profileViewModel.getUserProfileGluten("${getUserName()}Gluten").observe(this,
            androidx.lifecycle.Observer {updateProfileGluten(it)})


        user = getUserName()
        username.text = "User: ${user.toString()}"

        //profileViewModel.setUserProfilePass(getUserName(), password)

        saveButton.setOnClickListener {
            if(dairy.isChecked){
                isDairys = true
                profileViewModel.setUserProfileDairy(getUserName(), isDairys)
            }else{
                profileViewModel.setUserProfileDairy(getUserName(), false)
            }

            if(veggie.isChecked){
                isVeggies = true
                profileViewModel.setUserProfileVeggie(getUserName(), isVeggies)
            }else{
                profileViewModel.setUserProfileVeggie(getUserName(), false)
            }

            if(vegan.isChecked){
                isVegans = true
                profileViewModel.setUserProfileVegan(getUserName(), isVegans)
            }else{
                profileViewModel.setUserProfileVegan(getUserName(), false)
            }

            if(gluten.isChecked){
                isGlutens = true
                profileViewModel.setUserProfileGluten(getUserName(), isGlutens)
            }else{
                profileViewModel.setUserProfileGluten(getUserName(), false)
            }

        }
    }

    private fun updateProfileDairy(dairys: Boolean) {
        isDairys = dairys
        if (isDairys) {
            dairy.setChecked(true)
        }
    }

    private fun updateProfileVeggie(veggies: Boolean){
        isVeggies = veggies
        if (isVeggies) {
            veggie.setChecked(true)
        }
    }

    private fun updateProfileVegan(vegans: Boolean){
        isVegans = vegans
        if(isVegans) {
            vegan.setChecked(true)
        }
    }

    private fun updateProfileGluten(glutens: Boolean) {
        isGlutens = glutens
        if(isGlutens) {
            gluten.setChecked(true)
        }
    }
    //I am going to need to refactor all the stuff I just did in Main. Profile should be updated
    //in this activity. There will need to be an edit button

}