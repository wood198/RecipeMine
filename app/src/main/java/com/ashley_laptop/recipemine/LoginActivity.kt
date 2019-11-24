package com.ashley_laptop.recipemine

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            //check the username and password
            //if they are in the system and if password is correct for username entered

            //if all info is correct:
            //startActivity(Intent(this, MainActivity::class.java).apply { putExtra("username", enterUsername.text) })
            //else text of error message becomes existant
        }
    }

}