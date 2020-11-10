package com.cocktails.views

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cocktails.models.CocktailsListResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cocktail_details.*

class CocktailDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cocktail_details)

        getCocktailDetails()
    }

    private fun getCocktailDetails() {
        val gson = Gson()
        val cocktailDataAsString =
            intent.getStringExtra("cocktailDataAsString")
        val cocktailData: CocktailsListResponse = gson.fromJson(
            cocktailDataAsString,
            CocktailsListResponse::class.java
        )
        setCocktailsDetailsToView(cocktailData)
    }

    private fun setCocktailsDetailsToView(cocktailData: CocktailsListResponse) {
        val cocktailImage: ImageView = cocktail_image
        val cocktailName: TextView = cocktail_name
        val cocktailCategory: TextView = cocktail_category
        val cocktailInstructions: TextView = cocktail_instructions
        val cocktailIngredient: TextView = cocktail_ingredient

        Picasso.get().load(cocktailData.strDrinkThumb).into(cocktailImage)
        cocktailName.text = (getString(R.string.cocktail_name) + cocktailData.strDrink)
        cocktailCategory.text = (getString(R.string.cocktail_category) + cocktailData.strCategory)
        cocktailInstructions.text =
            (getString(R.string.cocktail_instruction) + cocktailData.strInstructions)
        cocktailIngredient.text =
            (getString(R.string.cocktail_ingredients) + cocktailData.strIngredient1 + "," +
                    cocktailData.strIngredient2 + "," + cocktailData.strIngredient3 + "," + cocktailData.strIngredient4)

    }
}