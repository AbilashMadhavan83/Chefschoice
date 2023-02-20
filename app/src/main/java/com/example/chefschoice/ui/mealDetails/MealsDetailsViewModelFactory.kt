package com.example.chefschoice.ui.mealDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chefschoice.data.model.MealDetail
import com.example.chefschoice.data.repository.AppRepository


class MealsDetailsViewModelFactory(private val repository: AppRepository,private val  meals: MealDetail?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealsDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealsDetailsViewModel(repository,meals) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
