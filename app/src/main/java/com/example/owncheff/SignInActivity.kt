
/* !!!DEPRICATED!!!!
package com.example.owncheff

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


//==================================================================================================
// NOTE:
// Setting this activity as the one that a launches requires that the exported attrib
// of the activity tag in the manifest file is set to true.
// ...

// Q:
// Should this activity have its own layout file or should it use the main_activity layout?
// or maybe
// we go from signin activity -> main activity
//                                         user does stuff
//                                         user want to sign out
//                                         main activity pass some data to signin activity to express that
//           signin activity <- main activity
//  signin activity signs user out using google sign in client
//==================================================================================================
const val GOOGLE_SIGNOUT_REQ = "shouldSignOutFromGoogle"
const val USER_ACCOUNT_DATA = "userAccountData"
class SignInActivity : AppCompatActivity() {
    private val TAG = "SignInActivity"
    private val RC_SIGN_IN = 9001
    private var mGoogleSignInClient : GoogleSignInClient? = null
    private val mStatusTV : TextView? = null

    // HANDLER IMPLEMENTATIONS
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        findViewById<TextView>(R.id.sign_inStatusTV)

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in  DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // Button Customization
        val signInButton = findViewById<SignInButton>(R.id.sign_inButton)
        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setOnClickListener(){ googleSignIn() }
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT)
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
    var mainActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
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
            findViewById<View>(R.id.sign_inButton).visibility = View.VISIBLE
            findViewById<View>(R.id.sign_out_and_disconnectButton).visibility = View.GONE
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
        intent.putExtra(USER_ACCOUNT_DATA, userDisplayName)
        mainActivityResultLauncher.launch(intent)
        //context.startActivity(intent)
    }
}

*/