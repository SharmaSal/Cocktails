package com.cocktails.contracts

import android.content.Context
import com.cocktails.models.CocktailsListResponse

interface CocktailsListActivityContract {

    interface View {
        fun onGetDataSuccess(list: List<CocktailsListResponse?>?)
        fun onGetDataFailure(message: String?)
    }

    interface Presenter {
        fun getDataFromURL(context: Context?, url: String?)
    }

    interface Interactor {
        fun initRetrofitCall(context: Context?, url: String?)
    }

    interface onGetDataListener {
        fun onSuccess(cocktailsList: List<CocktailsListResponse?>?)
        fun onFailure(message: String?)
    }
}
