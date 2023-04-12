package com.example.owncheff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    val usernameEt = findViewById<EditText>(R.id.usernameEt)
    val passwordEt = findViewById<EditText>(R.id.passwordEt)
    val button = findViewById<Button>(R.id.loginBtn)


        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

}