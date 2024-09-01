package com.example.cocktailcraft

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class CocktailAdapter(val drinks:ArrayList<Drink>,val context: Context): Adapter<CocktailAdapter.CocktailViewHolder>() {

    inner class CocktailViewHolder(view: View):ViewHolder(view){


        val cocktailImage = view.findViewById<ImageView>(R.id.cocktailImg)
        val cocktailNameTv = view.findViewById<TextView>(R.id.cocktailNameTv)
        val cocktailInstruction = view.findViewById<TextView>(R.id.drinkInstructions)
        val ingredientsTextView: TextView = view.findViewById(R.id.ingredientsTextView)
        val measuresTextView: TextView = view.findViewById(R.id.measuresTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.drink_item,parent,false)

        return CocktailViewHolder(view)
    }

    override fun getItemCount(): Int {
       return drinks.size
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {

        val drink = drinks.get(position)
        val ingredientsWithMeasures = drink.getIngredientsWithMeasures()

        val ingredientsText = ingredientsWithMeasures.joinToString("\n") { it.first }
        val measuresText = ingredientsWithMeasures.joinToString("\n") { it.second }

            Glide.with(context)
                .load(drink.strDrinkThumb)
                .into(holder.cocktailImage)

            holder.cocktailNameTv.text = drink.strDrink ?:"No Name"
        holder.cocktailInstruction.text = drink.strInstructions
        holder.ingredientsTextView.text = ingredientsText
        holder.measuresTextView.text = measuresText
        }
    }
