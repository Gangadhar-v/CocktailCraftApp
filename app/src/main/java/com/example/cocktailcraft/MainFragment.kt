package com.example.cocktailcraft

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {
    private lateinit var editText:EditText
    private lateinit var btn:Button



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_main, container, false)
        editText = view.findViewById(R.id.editText)
        btn = view.findViewById(R.id.btn)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn.setOnClickListener {

            val input = editText.text.toString()
            val data = RetrofitInstance.retrofitService.getDrinks(input)
            data.enqueue(object: Callback<Drinks> {
                override fun onResponse(call: Call<Drinks>, response: Response<Drinks>) {

                    if (response.isSuccessful && response.body() != null) {

                        val drinkList = response.body()?.drinks
                        if (!drinkList.isNullOrEmpty() && drinkList.all { true }) {

                            val bundle = Bundle()
                            bundle.putSerializable("d", ArrayList(drinkList))
                            findNavController().navigate(
                                R.id.action_mainFragment_to_drinksFragment,
                                bundle
                            )
                        } else {
                            Toast.makeText(
                                this@MainFragment.requireContext(),
                                "No Cocktail found",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(this@MainFragment.requireContext(),"Network request failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Drinks>, t: Throwable) {
                    Toast.makeText(this@MainFragment.requireContext(),t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }

    }


}
