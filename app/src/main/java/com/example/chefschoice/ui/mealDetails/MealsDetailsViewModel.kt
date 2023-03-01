package com.example.chefschoice.ui.mealDetails
import androidx.lifecycle.*
import com.example.chefschoice.data.model.MealDetail
import com.example.chefschoice.data.model.MealInformation
import com.example.chefschoice.data.repository.AppRepository
import kotlinx.coroutines.launch


class MealsDetailsViewModel(private val repository: AppRepository,private val  meals: MealInformation?) : ViewModel() {
    private val _mealDetail = MutableLiveData<MealInformation?>()
    val mealDetail: LiveData<MealInformation?> = _mealDetail

    init {

        _mealDetail.value = meals
    }




    fun insertFavorite() = viewModelScope.launch {
        repository.insertFavorites(_mealDetail.value as MealInformation)
    }



}