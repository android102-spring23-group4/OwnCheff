package com.example.owncheff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        var database = (application as OwnCheffApp).database
        //TODO: first & last name
        val usernameET = findViewById<EditText>(R.id.regUsernameET)
        val passwordET = findViewById<EditText>(R.id.regPasswordET)
        val emailET = findViewById<EditText>(R.id.regEmailET)
        val submitButton = findViewById<Button>(R.id.registerBtn)

        val usernameInitial :String? = getIntent().getStringExtra(USER_ACCOUNT_USERNAME)
        val passwordInitial :String? = getIntent().getStringExtra(USER_ACCOUNT_PASSWORD)

        // AUTO-FILL USER FORM
        usernameET.setText(usernameInitial)
        passwordET.setText(passwordInitial)

        submitButton.setOnClickListener {
            //TODO: validate information
            //validate EditText input
            val userName : String  = usernameET.text.toString()
            val password : String  = passwordET.text.toString()
            val email    : String  = emailET.text.toString()
            val roleId : Long = UserRoles.USER.id
            //val settings : OwnCheffSettings = OwnCheffSettings() // define setting and provide defaults
            //val recipe_list_id : OwnCheffSettings = OwnCheffSettings() // TODO: create a new recipe table??? or have one recipe table and asign a user id to each recipe?

            //TODO: make db entry for new user
            lifecycleScope.launch(Dispatchers.IO) {
                database.userDao().insertOne(
                    UserEntity(
                        username = userName,
                        password = password,
                        email = email,
                        role = roleId))
            }
            finishRegistrationAndReturn()
        }
    }
    fun finishRegistrationAndReturn() {
        val intent = Intent()
        //TODO: pass actual registration info back to login? or do setup here
        //intent.putExtra(GOOGLE_SIGNOUT_REQ, true)
        setResult(RESULT_OK, intent)
        finish() // closes the activity, pass data to signin activity
    }
}