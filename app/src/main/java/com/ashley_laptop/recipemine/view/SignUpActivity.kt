package com.ashley_laptop.recipemine.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.ashley_laptop.recipemine.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        enterUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        signUpButton.setOnClickListener {
            //check to see if already in the system
            //if they are then change error message to "already exists"
            //else send them to the login screen: (pop up account created??)
            /*intent = Intent(this, LoginActivity::class.java)
              startActivity(intent)
            */
        }
    }
}