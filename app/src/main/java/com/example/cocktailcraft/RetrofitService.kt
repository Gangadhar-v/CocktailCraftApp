package com.example.cocktailcraft

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search.php")
    fun getDrinks(
        @Query("s") s:String
    ): Call<Drinks>

}