package com.example.chefschoice.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.chefschoice.data.model.Meal
import com.example.chefschoice.databinding.CategoryCardBinding

class MealItemAdapter(
    private var meals: List<Meal>,
    private val iMeal: IMeals
) : RecyclerView.Adapter<MealItemAdapter.MealViewHolder>(){
    inner class MealViewHolder(val binding: CategoryCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = CategoryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        with(holder){
            with(meals[position]){
                binding.tvCategoryName .text = this.strMeal
                binding.imgCategory .load(this.strMealThumb){
                    build()
                }
                binding.imgCategoryBorder .setOnClickListener {
                    iMeal.onCellClickListener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return meals.size
    }

}