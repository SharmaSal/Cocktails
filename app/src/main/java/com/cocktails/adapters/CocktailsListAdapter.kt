package com.cocktails.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.cocktails.models.CocktailsListResponse
import com.cocktails.views.R
import kotlinx.android.synthetic.main.cocktails_list_data.view.*

class CocktailsListAdapter(
    list: List<CocktailsListResponse?>?,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var cocktailsList: List<CocktailsListResponse?>? = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CocktailsListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cocktails_list_data, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cocktailsList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CocktailsListViewHolder -> {
                cocktailsList?.get(position)
                holder.bind(cocktailsList?.get(position), itemClickListener)
            }
        }
    }

    class CocktailsListViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var cocktailName: TextView = itemView.cocktail_name
        private var cocktailImage: ImageView = itemView.cocktail_image

        fun bind(cocktails: CocktailsListResponse?, clickListener: OnItemClickListener) {

            val circularProgressDrawable = CircularProgressDrawable(itemView.context)

            val requestOptions = RequestOptions()
                .placeholder(circularProgressDrawable)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(cocktails?.strDrinkThumb)
                .into(cocktailImage)

            cocktailName.text = cocktails?.strDrink

            itemView.setOnClickListener {
                if (cocktails != null) {
                    clickListener.onItemClicked(cocktails)
                }
            }
        }
    }

    fun loadImage() {

    }

    interface OnItemClickListener {
        fun onItemClicked(cocktails: CocktailsListResponse?)
    }
}