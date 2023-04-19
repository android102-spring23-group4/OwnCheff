package com.example.owncheff

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val GOOGLE_SIGNOUT_REQ = "shouldSignOutFromGoogle"
const val USER_ACCOUNT_USERNAME = "userAccountUsername"
const val USER_ACCOUNT_PASSWORD = "userAccountPassword"
class LoginActivity : AppCompatActivity() {
    // GOOGLE SIGN-IN
    private val TAG = "SignInActivity"
    private val RC_SIGN_IN = 9001
    private var mGoogleSignInClient : GoogleSignInClient? = null
    private val mStatusTV : TextView? = null
    // GOOGLE SIGN-IN
    private lateinit var mainActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var registrationActivityResultLauncher: ActivityResultLauncher<Intent>

    // HANDLER IMPLEMENTATIONS
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // DEFAULT SIGN-IN
        val usernameEt = findViewById<EditText>(R.id.usernameEt)
        val passwordEt = findViewById<EditText>(R.id.passwordEt)
        val button = findViewById<Button>(R.id.loginBtn)
        val signupTv = findViewById<TextView>(R.id.signupTv)
        button.setOnClickListener {
            //TODO: implement some sort of check for account here once db is implemented.
            //TODO: look up user by user name then check id

            // if user found and credentials correct
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // else hint user to signup
        }
        signupTv.setOnClickListener {
            val userName : String  = usernameEt.text.toString()
            val password : String  = passwordEt.text.toString()

            goToRegistrationActivity(userName, password)
        }

        // GOOGLE SIGN-IN
        findViewById<TextView>(R.id.sign_inStatusTV)
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in  DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // Button Customization
        val signInButton = findViewById<SignInButton>(R.id.sign_inButton)
        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setOnClickListener() { googleSignIn() }
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT)


        // LOGIC WHEN RETURNING TO THIS ACTIVITY
        mainActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // If the user comes back to this activity from MainActivity
            // with no error or cancellation
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                // Get the data passed from EditActivity
                if (data != null) {
                    val shouldSignOut = data.extras!!.getBoolean(GOOGLE_SIGNOUT_REQ)
                    if(shouldSignOut) {  googleSignOut() }
                }
            }
        }
        registrationActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                goToMainActivity("User returned from registration") //TODO: actually pass username
            }
        }
    }
    override fun onStart()
    {
        super.onStart()
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //TODO: figure out what the non deprecated approach to do this is

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }


    // HELPER FUNCTIONS
    private fun googleSignIn() {
        //TODO: find the modern way to do this
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    private fun googleSignOut() {
        // NOTE: signout moved to main activity
        mGoogleSignInClient!!.signOut()
            .addOnCompleteListener(this) { updateUI(null) }
    }
    private fun googleRevokeAccess() {
        mGoogleSignInClient!!.revokeAccess()
            .addOnCompleteListener(this) { updateUI(null) }
    }
    private fun updateUI(account: GoogleSignInAccount?) {
        // NOTE: This is from googles example code. This may need to be in another fragment or something
        // NOTE: This function may need to be removed as it is not usefull
        if (account != null) {
            goToMainActivity(account.displayName!!)
            //mStatusTV!!.text = getString(R.string.signed_in_fmt, account.displayName)
            //findViewById<View>(R.id.sign_inButton).visibility = View.GONE
            //findViewById<View>(R.id.sign_out_and_disconnectButton).visibility = View.VISIBLE
        } else {
            mStatusTV?.setText(R.string.signed_out)
            //findViewById<View>(R.id.sign_inButton).visibility = View.VISIBLE
            //findViewById<View>(R.id.sign_out_and_disconnectButton).visibility = View.GONE
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }
    private fun goToMainActivity(userDisplayName : String)
    {
        val context = this
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(USER_ACCOUNT_USERNAME, userDisplayName)
        mainActivityResultLauncher.launch(intent)
    }
    private fun goToRegistrationActivity(username : String, password : String)
    {
        val context = this
        val intent = Intent(context, RegistrationActivity::class.java)
        intent.putExtra(USER_ACCOUNT_USERNAME, username)
        intent.putExtra(USER_ACCOUNT_PASSWORD, password)
        registrationActivityResultLauncher.launch(intent)
    }
}