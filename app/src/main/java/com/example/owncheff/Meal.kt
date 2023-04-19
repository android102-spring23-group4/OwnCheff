package com.example.owncheff

import com.google.gson.annotations.SerializedName
//This is the meal item and what it is taking from the JSON string

class Meal {

    @JvmField
    @SerializedName("strMeal")
    var name: String? = null

    @SerializedName("strArea")
    var country: String? = null


    @SerializedName("strMealThumb")
    var image: String? = null

    @SerializedName("strInstructions")
    var description: String? = null
}