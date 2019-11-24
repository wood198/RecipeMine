package com.ashley_laptop.recipemine

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signUpButton.setOnClickListener {
            //check to see if already in the system
            //if they are then change error message to "already exists"
            //else send them to the login screen:
            /*intent = Intent(this, LoginActivity::class.java)
              startActivity(intent)
            */
        }
    }
}