package com.cocktails.views


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cocktails.adapters.CocktailsListAdapter
import com.cocktails.contracts.CocktailsListActivityContract
import com.cocktails.models.CocktailsListResponse
import com.cocktails.presenters.CocktailsListActivityPresenter
import com.cocktails.utility.CheckNetworkConnectivity
import com.cocktails.utility.TopSpacing
import com.google.gson.Gson
import kotlinx.android.synthetic.main.cocktails_list.*


class CocktailsListActivity : AppCompatActivity(), CocktailsListActivityContract.View,
    CocktailsListAdapter.OnItemClickListener {
    private lateinit var myadapter: CocktailsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cocktails_list)

        getDataFromServer()
        initRecyclerView()
        recycler_view.callOnClick()
    }

    private fun getDataFromServer() {
        if (CheckNetworkConnectivity.checkIfNetworkAvailable(this)) {
            val presenter = CocktailsListActivityPresenter(this)
            presenter.getDataFromURL(this, "")
        } else {
            showMessage(getString(R.string.no_network_message))
        }
    }

    private fun initRecyclerView() {
        recycler_view
            .apply {
                layoutManager = LinearLayoutManager(this@CocktailsListActivity)
                val topSpacingDecorator = TopSpacing(30)
                addItemDecoration(topSpacingDecorator)
            }
    }

    override fun onGetDataSuccess(list: List<CocktailsListResponse?>?) {
        progressBar.visibility = View.GONE
        if (list == null) {
            showMessage(getString(R.string.no_data_available))
        } else {
            myadapter = CocktailsListAdapter(list, this)
            recycler_view.adapter = myadapter
        }
    }

    override fun onGetDataFailure(message: String?) {
        showMessage(message)
    }

    override fun onItemClicked(cocktails: CocktailsListResponse?) {
        val intent = Intent(this, CocktailDetailsActivity::class.java)
        val gson = Gson()
        val cocktailAsAString = gson.toJson(cocktails)
        intent.putExtra("cocktailDataAsString", cocktailAsAString)
        startActivity(intent)
    }

    private fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}





