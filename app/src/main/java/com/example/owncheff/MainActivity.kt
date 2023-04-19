package com.example.owncheff

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Button
import android.widget.TextView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers


class MainActivity : AppCompatActivity() {
    val TAG = "Main Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // MAIN STUFF
        val recipeFragment: Fragment = RecipeFragment()
        val ingredientsFragment: Fragment = IngredientsFragment()
        val profileFragment: Fragment = ProfileFragment()

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
        val signOutButton = findViewById<Button>(R.id.sign_out_and_disconnectButton)
        val userName :String? = getIntent().getStringExtra(USER_ACCOUNT_USERNAME)
        val greetingsTV = findViewById<TextView>(R.id.greetingsTV)
        greetingsTV.text = getString(R.string.user_greeting, userName)
        signOutButton.setOnClickListener() { signOut() }


        // TEST API REQUEST
        val ARTICLE_SEARCH_URL = "https://www.themealdb.com/api/json/v1/1/random.php"
        val client = AsyncHttpClient()
        client.get(ARTICLE_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched articles: $json")
                //json.jsonObject.toString()
            }
        })
        // TEST API REQUEST
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