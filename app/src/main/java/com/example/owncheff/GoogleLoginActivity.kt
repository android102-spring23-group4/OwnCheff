package com.example.owncheff

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException

class GoogleLoginActivity : AppCompatActivity() {

    private lateinit var sign_in_button: SignInButton
    private val RC_SIGN_IN = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_login)

        sign_in_button = findViewById<SignInButton>(R.id.sign_in_button)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(this, gso)

        sign_in_button.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val email = account.email
                val name = account.displayName
                val firstName = account.givenName
                val lastName = account.familyName
                val profilePicture = account.photoUrl.toString()
                // Successfully signed in
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("firstname",firstName)
                intent.putExtra("lastname",lastName)
                intent.putExtra("email",email)
                intent.putExtra("displayname",name)
                intent.putExtra("pfp",profilePicture)
                startActivity(intent)
                finish()
            } catch (e: ApiException) {
                // Sign-in failed
                when (e.statusCode) {
                    GoogleSignInStatusCodes.SIGN_IN_CANCELLED -> {
                        Toast.makeText(this, "sign in interrupted", Toast.LENGTH_SHORT).show()
                    }
                    GoogleSignInStatusCodes.NETWORK_ERROR -> {
                        Toast.makeText(this, "lost connection", Toast.LENGTH_SHORT).show()
                    }
                    GoogleSignInStatusCodes.INTERNAL_ERROR -> {
                        Toast.makeText(this, "error in program", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this, "Error: ${e.statusCode}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}