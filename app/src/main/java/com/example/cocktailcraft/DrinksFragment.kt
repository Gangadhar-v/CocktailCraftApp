package com.example.cocktailcraft

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView



class DrinksFragment : Fragment() {
    private lateinit var tv:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_drinks, container, false)
        tv = view.findViewById(R.id.f2tv)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        val drinks: ArrayList<Drink>? = args?.getSerializable("d") as? ArrayList<Drink>
        tv.text = drinks?.joinToString()

    }


    }
