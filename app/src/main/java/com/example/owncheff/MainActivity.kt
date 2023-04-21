package com.example.owncheff

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // MAIN STUFF
        val recipeFragment: Fragment = RecipeFragment()
        val ingredientsFragment: Fragment = IngredientsFragment()
        val profileFragment: Fragment = ProfileFragment()


        /*val acct = intent.getParcelableExtra<GoogleSignInAccount>("google_account")*/
        val email = intent.getStringExtra("email")
        val firstname = intent.getStringExtra("firstname")
        val lastname = intent.getStringExtra("lastname")
        val displayname = intent.getStringExtra("displayname")
        val profilepicture = intent.getStringExtra("pfp")

        val bundle = Bundle()
        bundle.putString("email", email)
        bundle.putString("name", displayname)
        bundle.putString("firstname",firstname)
        bundle.putString("lastname",lastname)
        bundle.putString("profilepic",profilepicture)
        profileFragment.arguments = bundle


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_recipes-> fragment = recipeFragment
                R.id.nav_ingredients -> fragment = ingredientsFragment
                R.id.nav_profile -> fragment = profileFragment
            }
            replaceFragment(fragment)
            true
        }
        bottomNavigationView.selectedItemId = R.id.nav_recipes


        // SIGN-OUT STUFF
        val userName :String? = displayname
        val greetingsTV = findViewById<TextView>(R.id.greetingsTV)
        greetingsTV.text = getString(R.string.user_greeting, userName)
    }

    // HELPERS
    fun signOut() {
        val intent = Intent()
        intent.putExtra(GOOGLE_SIGNOUT_REQ, true)
        setResult(RESULT_OK, intent)
        finish() // closes the activity, pass data to signin activity
    }
    private fun replaceFragment(recipeFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, recipeFragment)
        fragmentTransaction.commit()
    }
}