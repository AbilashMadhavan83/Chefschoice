package com.example.chefschoice.ui.home

import com.example.chefschoice.data.model.PopularMeal

interface IPopularMeal {
    fun onCellClickListener(popularMeal: PopularMeal)
}