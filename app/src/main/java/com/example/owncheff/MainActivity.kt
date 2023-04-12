package com.example.owncheff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        }

    private fun replaceFragment(recipeFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, recipeFragment)
        fragmentTransaction.commit()
    }
}