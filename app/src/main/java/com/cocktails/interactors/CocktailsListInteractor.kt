package com.cocktails.interactors

import android.content.Context
import com.cocktails.contracts.CocktailsListActivityContract
import com.cocktails.models.CocktailsList
import com.cocktails.models.CocktailsListApi
import com.cocktails.models.CocktailsListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CocktailsListInteractor(private var mOnGetDatalistener: CocktailsListActivityContract.onGetDataListener?) :
    CocktailsListActivityContract.Interactor {

    var cocktailsList: List<CocktailsListResponse?>? = ArrayList()

    override fun initRetrofitCall(context: Context?, url: String?) {
        val service: CocktailsListApi
        val retrofit = Retrofit.Builder().baseUrl("https://www.thecocktaildb.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(CocktailsListApi::class.java)

        val call: Call<CocktailsList?> = service.getData()
        call.enqueue(object : Callback<CocktailsList?> {
            override fun onResponse(
                call: Call<CocktailsList?>,
                response: Response<CocktailsList?>
            ) {
                val jsonResponse = response.body()
                if (jsonResponse != null) {
                    cocktailsList = jsonResponse.drinks
                }
                mOnGetDatalistener?.onSuccess(cocktailsList)
            }

            override fun onFailure(call: Call<CocktailsList?>, t: Throwable) {
                mOnGetDatalistener?.onFailure("service failed")
            }


        })
    }

}
