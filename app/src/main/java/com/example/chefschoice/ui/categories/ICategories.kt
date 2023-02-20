package com.example.chefschoice.ui.categories
import com.example.chefschoice.data.model.MealCategory

interface ICategories {
    fun onCellClickListener(category: MealCategory)
}