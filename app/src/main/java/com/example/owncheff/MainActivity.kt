package com.example.owncheff


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signOutButton = findViewById<Button>(R.id.sign_out_and_disconnectButton)
        val userName :String? = getIntent().getStringExtra(USER_ACCOUNT_DATA)
        val greetingsTV = findViewById<TextView>(R.id.greetingsTV)
        greetingsTV.text = getString(R.string.user_greeting, userName)
        signOutButton.setOnClickListener() { signOut() }
    }
    fun signOut() {
        val intent = Intent()
        intent.putExtra(GOOGLE_SIGNOUT_REQ, true)
        setResult(RESULT_OK, intent)
        finish() // closes the activity, pass data to signin activity
    }
}