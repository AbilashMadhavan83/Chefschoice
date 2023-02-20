package com.example.chefschoice.ui.mealDetails
import androidx.lifecycle.*
import com.example.chefschoice.data.model.MealDetail
import com.example.chefschoice.data.model.MealTable
import com.example.chefschoice.data.repository.AppRepository
import kotlinx.coroutines.launch


class MealsDetailsViewModel(private val repository: AppRepository,private val  meals: MealDetail?) : ViewModel() {
    private val _mealDetail = MutableLiveData<MealTable?>()
    val mealDetail: LiveData<MealTable?> = _mealDetail

    init {

        _mealDetail.value = MealTable(
            mealId              = meals?.idMeal?.toInt() ?: 0,
            mealName            = meals?.strMeal.toString(),
            mealCountry         = meals?.strArea .toString(),
            mealCategory        = meals?.strCategory.toString(),
            mealInstruction     = meals?.strInstructions.toString(),
            mealThumb           = meals?.strMealThumb.toString(),
            mealYoutubeLink     = meals?.strYoutube.toString()
        )
    }





    fun insertFavorite() = viewModelScope.launch {
        //_mealDetail.value?.let { repository.insertFavorite(it) }
    }

}