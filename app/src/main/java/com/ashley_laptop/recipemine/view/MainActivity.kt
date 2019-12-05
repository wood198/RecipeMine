package com.ashley_laptop.recipemine.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ashley_laptop.recipemine.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private fun getUserName() = intent.extras?.get("username").toString().trim()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = getUserName()
        Log.d("linz2", "username: ${user}")


        goToProfile.setOnClickListener {

            startActivity(Intent(this, ProfileActivity::class.java).apply { putExtra("username", user) })

        }
        //disable back button to the other activities

    }


}
