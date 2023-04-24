package com.example.owncheff

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.owncheff.Meal.IngredientsFragment
import com.example.owncheff.Meal.Meal
import com.example.owncheff.Meal.MealEntity
import com.example.owncheff.Meal.RecipeFragment
import com.example.owncheff.ingredients.Ingredients
import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.example.owncheff.ingredients.IngredientsFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Headers
import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = (application as OwnCheffApp).data
        val ARTICLE_SEARCH_URL = "https://www.themealdb.com/api/json/v1/1/random.php"
        val client = AsyncHttpClient()
        client.get(ARTICLE_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(ControlsProviderService.TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {

                Log.i(ControlsProviderService.TAG, "Successfully fetched articles: $json")
                val results = json!!.jsonObject["meals"] as JSONArray
                val gson = Gson()
                val Recipe = object : TypeToken<List<Meal>>() {}.type
                val models: List<Meal> = gson.fromJson(results.toString(), Recipe)


                lifecycleScope.launch(Dispatchers.IO) {
                    database.mealDao().insertOne(
                        MealEntity(
                            recipe_name = models[0].mealName


                        )
                    )
                }
            }
        })
        // MAIN STUFF
        val recipeFragment: Fragment = RecipeFragment()
        val ingredientsFragment: Fragment = IngredientsFragment()
        val profileFragment: Fragment = ProfileFragment()

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
        //val signOutButton = findViewById<Button>(R.id.sign_out_and_disconnectButton)
        val userName :String? = getIntent().getStringExtra(USER_ACCOUNT_USERNAME)
        //val greetingsTV = findViewById<TextView>(R.id.greetingsTV)
        //greetingsTV.text = getString(R.string.user_greeting, userName)
        //signOutButton.setOnClickListener() { signOut() }
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