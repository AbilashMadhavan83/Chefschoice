package com.example.chefschoice.ui.categories

import com.example.chefschoice.data.model.Meal
import com.example.chefschoice.data.model.MealCategory

interface IMeals {
    fun onCellClickListener(meal: Meal)
}