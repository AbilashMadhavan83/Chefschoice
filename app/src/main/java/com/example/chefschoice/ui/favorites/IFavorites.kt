package com.example.chefschoice.ui.favorites

import com.example.chefschoice.data.model.Meal
import com.example.chefschoice.data.model.MealCategory
import com.example.chefschoice.data.model.MealInformation

interface IFavorites {
    fun onCellClickListener(meal: MealInformation?)
}