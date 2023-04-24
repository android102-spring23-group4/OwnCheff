package com.example.owncheff.ingredients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.owncheff.Meal.IngredientsFragment
import com.example.owncheff.Meal.OnListFragmentInteractionListener
import com.example.owncheff.Meal.OnListFragmentInteractionListener2
import com.example.owncheff.R

class IngredientsRVAdapter(private val ingredient: List<Ingredients>,
                           private val mListener: OnListFragmentInteractionListener2
)
    : RecyclerView.Adapter<IngredientsRVAdapter.BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_ingredients, parent, false)
        return BookViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        //variables for the views and images
        var mItem: Ingredients? = null
        val mIngredient1: TextView = mView.findViewById<View>(R.id.textView) as TextView
        val mIngredient2: TextView = mView.findViewById<View>(R.id.textView2) as TextView
        val mIngredient3: TextView = mView.findViewById<View>(R.id.textView3) as TextView
        val mIngredient4: TextView = mView.findViewById<View>(R.id.textView4) as TextView
        val mIngredient5: TextView = mView.findViewById<View>(R.id.textView5) as TextView
        val mIngredient6: TextView = mView.findViewById<View>(R.id.textView6) as TextView
        val mIngredient7: TextView = mView.findViewById<View>(R.id.textView7) as TextView
        val mIngredient8: TextView = mView.findViewById<View>(R.id.textView8) as TextView
        val mIngredient9: TextView = mView.findViewById<View>(R.id.textView9) as TextView
        val mIngredient10: TextView = mView.findViewById<View>(R.id.textView10) as TextView
        val mIngredient11: TextView = mView.findViewById<View>(R.id.textView11) as TextView
        val mIngredient12: TextView = mView.findViewById<View>(R.id.textView12) as TextView
        val mIngredient13: TextView = mView.findViewById<View>(R.id.textView13) as TextView
        val mIngredient14: TextView = mView.findViewById<View>(R.id.textView14) as TextView
        val mIngredient15: TextView = mView.findViewById<View>(R.id.textView15) as TextView
        val mIngredient16: TextView = mView.findViewById<View>(R.id.textView16) as TextView
        val mIngredient17: TextView = mView.findViewById<View>(R.id.textView17) as TextView
        val mIngredient18: TextView = mView.findViewById<View>(R.id.textView18) as TextView
        val mIngredient19: TextView = mView.findViewById<View>(R.id.textView19) as TextView
        val mIngredient20: TextView = mView.findViewById<View>(R.id.textView20) as TextView

        //override fun toString(): String {

      //  }
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        //Binding the view and images
        val recipe = ingredient[position]
        holder.mItem = recipe
        holder.mIngredient1.text = recipe.ingredient1
        holder.mIngredient2.text = recipe.ingredient2
        holder.mIngredient3.text = recipe.ingredient3
        holder.mIngredient4.text = recipe.ingredient4
        holder.mIngredient5.text = recipe.ingredient5
        holder.mIngredient6.text = recipe.ingredient6
        holder.mIngredient7.text = recipe.ingredient7
        holder.mIngredient8.text = recipe.ingredient8
        holder.mIngredient9.text = recipe.ingredient9
        holder.mIngredient10.text = recipe.ingredient10
        holder.mIngredient11.text = recipe.ingredient11
        holder.mIngredient12.text = recipe.ingredient12
        holder.mIngredient13.text = recipe.ingredient13
        holder.mIngredient14.text = recipe.ingredient14
        holder.mIngredient15.text = recipe.ingredient15
        holder.mIngredient16.text = recipe.ingredient16
        holder.mIngredient17.text = recipe.ingredient17
        holder.mIngredient18.text = recipe.ingredient18
        holder.mIngredient19.text = recipe.ingredient19
        holder.mIngredient20.text = recipe.ingredient20


        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }
        }


    }
    override fun getItemCount(): Int {
        return ingredient.size
    }
}