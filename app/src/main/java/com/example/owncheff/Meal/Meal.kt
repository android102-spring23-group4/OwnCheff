package com.example.owncheff.Meal

import com.google.gson.annotations.SerializedName
//This is the meal item and what it is taking from the JSON string

class Meal(
    @SerializedName("strMeal")
    var mealName: String? = null,

    @SerializedName("strInstructions")
    var mealInstructions: String? = null,

    @SerializedName("strMealThumb")
    var mealImage: String? = null,

    @SerializedName("strArea")
    var country: String? = null
)