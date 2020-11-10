package com.cocktails.presenters

import android.content.Context
import com.cocktails.contracts.CocktailsListActivityContract
import com.cocktails.interactors.CocktailsListInteractor
import com.cocktails.models.CocktailsListResponse

class CocktailsListActivityPresenter(private var mGetDataView: CocktailsListActivityContract.View?) :
    CocktailsListActivityContract.Presenter,
    CocktailsListActivityContract.onGetDataListener {
    private var mIntractor: CocktailsListInteractor = CocktailsListInteractor(this)

    override fun getDataFromURL(context: Context?, url: String?) {
        mIntractor.initRetrofitCall(context, url)
    }

    override fun onSuccess(cocktailsList: List<CocktailsListResponse?>?) {
        mGetDataView?.onGetDataSuccess(cocktailsList)
    }

    override fun onFailure(message: String?) {
        mGetDataView?.onGetDataFailure(message)
    }

}

