package com.example.owncheff.ingredients

import android.os.Bundle
import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.owncheff.Meal.Meal
import com.example.owncheff.Meal.MealRVAdapter
import com.example.owncheff.Meal.OnListFragmentInteractionListener
import com.example.owncheff.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

class IngredientsFragment : Fragment(), OnListFragmentInteractionListener {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ingredients_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress2) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list2) as RecyclerView
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
                Log.i(ControlsProviderService.TAG, "Successfully fetched articles: $json")
                val results = json!!.jsonObject["meals"] as JSONArray
                val gson = Gson()
                val Recipe = object : TypeToken<List<Meal>>() {}.type
                val models : List<Meal> = gson.fromJson(results.toString(), Recipe)
                recyclerView.adapter = MealRVAdapter(models, this@IngredientsFragment)
            }
        })
        progressBar.hide()
    }

    override fun onItemClick(item: Meal) {
        Toast.makeText(context, "test: " , Toast.LENGTH_LONG).show()
    }
}