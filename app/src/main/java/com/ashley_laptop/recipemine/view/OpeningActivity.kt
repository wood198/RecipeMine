package com.ashley_laptop.recipemine.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ashley_laptop.recipemine.R
import kotlinx.android.synthetic.main.activity_opening.*

class OpeningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)

        loginButton.setOnClickListener{

            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener{

            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

}