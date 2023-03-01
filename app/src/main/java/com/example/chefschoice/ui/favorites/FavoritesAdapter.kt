package com.example.chefschoice.ui.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.chefschoice.R
import com.example.chefschoice.data.model.MealInformation
import com.example.chefschoice.databinding.FavoritesCardBinding
import com.example.chefschoice.databinding.MealsCardBinding

class FavoritesAdapter(
    private var mealInformation: List<MealInformation>,
    private val iFavorites: IFavorites
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>(){
    inner class FavoritesViewHolder(val binding: FavoritesCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = FavoritesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {

        with(holder){
            with(mealInformation[position]){
                binding.mealName.text = this.mealName
                binding.mealCategory.text = this.mealCategory
                binding.mealCountry.text = this.mealCountry
                binding.Image.load(this.mealThumb){
                    build()
                }
                binding.card.setOnClickListener {
                    iFavorites.onCellClickListener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mealInformation.size
    }
}