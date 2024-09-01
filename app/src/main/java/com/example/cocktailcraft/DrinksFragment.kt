package com.example.cocktailcraft

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class DrinksFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var drinkAdapter:CocktailAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_drinks, container, false)
        recyclerView = view.findViewById(R.id.drinksRecyclerView)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        val drinks: ArrayList<Drink>? = args?.getSerializable("d") as? ArrayList<Drink>

        if(drinks != null){
            drinkAdapter = CocktailAdapter(drinks!!,this@DrinksFragment.requireContext())
            recyclerView.layoutManager = LinearLayoutManager(this@DrinksFragment.requireContext())
            recyclerView.adapter = drinkAdapter
        }else{
            Toast.makeText(this@DrinksFragment.requireContext(), "No drinks available", Toast.LENGTH_SHORT).show()
        }


    }


    }
