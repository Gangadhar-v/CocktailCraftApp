package com.example.cocktailcraft

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {
    private lateinit var editText:TextInputEditText
    private lateinit var btn:Button
    private lateinit var edtLyt: TextInputLayout
    private lateinit var progressBar:ProgressBar



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_main, container, false)
        editText = view.findViewById(R.id.edittext)
        btn = view.findViewById(R.id.btn)
        edtLyt = view.findViewById(R.id.edittextLyt)
        progressBar = view.findViewById(R.id.progressBar)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText.addTextChangedListener {
            if(it?.count()!! >0){
                edtLyt.error = null
            }
        }


        btn.setOnClickListener {

            val input = editText.text.toString()

            if (input.isNullOrEmpty()) {
                edtLyt.error = "Please enter Cocktail Name"
                Toast.makeText(this@MainFragment.requireContext(),"This field cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                progressBar.visibility = View.VISIBLE
                val data = RetrofitInstance.retrofitService.getDrinks(input)
                data.enqueue(object : Callback<Drinks> {
                    override fun onResponse(call: Call<Drinks>, response: Response<Drinks>) {

                        if (response.isSuccessful && response.body() != null) {

                            val drinkList = response.body()?.drinks
                            if (!drinkList.isNullOrEmpty()) { /* && drinkList.all { it != null}*/

                                val bundle = Bundle()
                                bundle.putSerializable("d", ArrayList(drinkList))
                                findNavController().navigate(
                                    R.id.action_mainFragment_to_drinksFragment,
                                    bundle
                                )
                                progressBar.visibility = View.GONE
                            } else {
                                Toast.makeText(
                                    this@MainFragment.requireContext(),
                                    "No Cocktail found",
                                    Toast.LENGTH_SHORT
                                ).show()
                                progressBar.visibility = View.GONE
                            }
                        } else {
                            Toast.makeText(
                                this@MainFragment.requireContext(),
                                "Network request failed",
                                Toast.LENGTH_SHORT
                            ).show()
                            progressBar.visibility = View.GONE
                        }
                    }

                    override fun onFailure(call: Call<Drinks>, t: Throwable) {
                        Toast.makeText(
                            this@MainFragment.requireContext(),
                            t.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        progressBar.visibility = View.GONE
                    }
                })
            }

        }
    }

}
