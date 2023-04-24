package com.example.owncheff.Meal

import android.os.Bundle
import android.service.controls.ControlsProviderService
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.owncheff.R
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONException

class RecipeFragment : Fragment(), OnListFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }


    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()
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
                try {
                    val mealArray = JSONArray(json.jsonObject.get("meals").toString())
                    val meal = mealArray.getJSONObject(0)
                    val mealName = meal.getString("strMeal")
                    val mealCategory = meal.getString("strCategory")
                    val mealInstructions = meal.getString("strInstructions")
                    val mealImage = meal.getString("strMealThumb")
                    val mealIngredients = ArrayList<String>()

                    // Parse the ingredients
                    for (i in 1..20) {
                        val ingredient = meal.getString("strIngredient$i")
                        if (!ingredient.isNullOrEmpty()) {
                            val measure = meal.getString("strMeasure$i")
                            mealIngredients.add("$ingredient - $measure")
                        }
                    }

                    // Create a new Meal object
                    val newMeal = Meal(
                        mealName,
                        mealInstructions,
                        mealImage,
                    )

                    // Update the adapter
                    val models = listOf(newMeal)
                    recyclerView.adapter = MealRVAdapter(models, this@RecipeFragment)
                } catch (e: JSONException) {
                    Log.e(ControlsProviderService.TAG, "Failed to parse response: $e")
                } finally {
                    progressBar.hide()
                }
            }
        })
        progressBar.hide()
    }

    override fun onItemClick(item: Meal) {
        Toast.makeText(context, "test: " , Toast.LENGTH_LONG).show()
    }
}