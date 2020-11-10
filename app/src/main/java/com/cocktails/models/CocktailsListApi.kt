package com.cocktails.models

import retrofit2.Call
import retrofit2.http.GET

interface CocktailsListApi {
    @GET("api/json/v1/1/search.php?s=margarita")
    fun getData(): Call<CocktailsList?>
}
