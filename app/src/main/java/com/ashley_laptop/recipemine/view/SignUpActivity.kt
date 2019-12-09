package com.ashley_laptop.recipemine.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ashley_laptop.recipemine.R
import com.ashley_laptop.recipemine.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.enterUsername

class SignUpActivity : AppCompatActivity(){
    private lateinit var profileViewModel: ProfileViewModel
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        enterUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        signUpButton.setOnClickListener {

            profileViewModel.getUserProfilePass("${enterUsername.text}Pass").observe(this,
                androidx.lifecycle.Observer {getPass(it)})

            Log.d("ash1", "password: ${password}")
            if(password == null){
                errorMessageSU.text = "Username taken"
            }else if(password == enterPass.text.toString()){
                errorMessageSU.text = "This account already exists"
            }else{
                profileViewModel.setUserProfilePass(enterUsername.text.toString(), enterPass.text.toString())
                startActivity(Intent(this, MainActivity::class.java).apply { putExtra("username", enterUsername.text) })
            }
        }

    }

    private fun getPass(pass: String){
        password = pass
    }
}