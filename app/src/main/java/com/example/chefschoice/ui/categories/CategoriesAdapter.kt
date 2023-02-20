package com.example.chefschoice.ui.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.chefschoice.R
import com.example.chefschoice.data.model.MealCategory
import com.example.chefschoice.databinding.MealsCardBinding

class CategoriesAdapter(
    private var context: Context,
    private var categories: List<MealCategory>,
    private val iCategory: ICategories
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>(){
    inner class CategoryViewHolder(val binding: MealsCardBinding) : RecyclerView.ViewHolder(binding.root)

    private var lastSelectedPosition :Int = RecyclerView.NO_POSITION
    private var isValid: Boolean = true
    private var previousSelectedPosition:Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = MealsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        with(holder){


            with(categories[position]){

                binding.imgCategoryBorder.setOnClickListener {
                    previousSelectedPosition = lastSelectedPosition
                    lastSelectedPosition = position
                    notifyItemChanged(previousSelectedPosition)
                    notifyItemChanged(lastSelectedPosition)
                    iCategory.onCellClickListener(this)
                }

                binding.txtStrMeal .text = this.strCategory
                binding.imgMeal .load(this.strCategoryThumb){
                    build()
                }



                if (isValid){
                    isValid = false
                    previousSelectedPosition = lastSelectedPosition
                    //lastSelectedPosition = position
                    iCategory.onCellClickListener(this)
                }

                if (position == lastSelectedPosition ) {
                    binding.imgCategoryBorder.strokeColor = ContextCompat.getColor(context, R.color.app_color)
                    binding.imgCategoryBorder.strokeWidth = 5
                } else{
                    binding.imgCategoryBorder.strokeWidth = 0
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}