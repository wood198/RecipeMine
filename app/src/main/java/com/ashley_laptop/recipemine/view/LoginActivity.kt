package com.ashley_laptop.recipemine.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ashley_laptop.recipemine.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        enterUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })


        loginButton.setOnClickListener {
            //check the username and password
            //if they are in the system and if password is correct for username entered

            //if all info is correct
            Log.d("linz", "username: ${enterUsername.text}")
            startActivity(Intent(this, MainActivity::class.java).apply { putExtra("username", enterUsername.text) })
            //else text of error message becomes existant
        }
    }

}