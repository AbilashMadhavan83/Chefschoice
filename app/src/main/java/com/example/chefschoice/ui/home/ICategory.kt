package com.example.chefschoice.ui.home
import com.example.chefschoice.data.model.MealCategory

interface ICategory {
    fun onCellClickListener(category: MealCategory?)
}