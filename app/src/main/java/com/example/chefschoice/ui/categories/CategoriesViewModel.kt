package com.example.chefschoice.ui.categories

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefschoice.data.repository.AppRepository
import com.example.chefschoice.data.enums.ApiStatus
import com.example.chefschoice.data.model.*
import kotlinx.coroutines.launch

class CategoriesViewModel(private val repository: AppRepository) : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()
    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus> = _status

    private val mealInformation = MutableLiveData<MealInformation?>()


    //category meals
    private val _category = MutableLiveData<List<MealCategory>?>()
    val category: LiveData<List<MealCategory>?> = _category

    //meals
    private val _meals = MutableLiveData<List<Meal>?>()
    val meals: LiveData<List<Meal>?> = _meals

    init {
        getCategories()

    }

    fun observeMealInformation(strMeal:String?): LiveData<MealInformation?> {
        getSingleRandomMeal(strMeal)
        return mealInformation
    }

    private fun getSingleRandomMeal(strMeal:String?) {

        viewModelScope.launch {
            _status.value = ApiStatus.LOADING


            try {
                val response: RandomMealResponse? = repository.getMeals(strMeal)


                if (response != null) {
                    with(response.meals[0]){

                        mealInformation.value = MealInformation(
                            mealId = idMeal?.toInt() ?: 0,
                            mealName = strMeal.toString(),
                            mealCountry = strArea.toString(),
                            mealCategory = strCategory.toString(),
                            mealInstruction = strInstructions.toString(),
                            mealThumb = strMealThumb.toString(),
                            mealYoutubeLink = strYoutube.toString()
                        )

                    }

                    //_singleRandomMeal.value = response.meals[0]
                    Log.d(
                        ContentValues.TAG,
                        "Chef's choice : " + "Successfully loaded single random meal."
                    )
                    _status.value = ApiStatus.DONE




                } else Log.d(
                    ContentValues.TAG,
                    "Chef's choice: " + "Single random meal loading failed."
                )


            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                //_singleRandomMeal.value = null
                Log.d(
                    ContentValues.TAG,
                    "Chef's choice: " + "Single random meal loading failed. Error :" + e.message
                )
            }


        }

    }

    fun getMealsByCategory(category: MealCategory){

        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val response: MealsResponse?  = repository.getMealsByCategory(category.strCategory)

                if (response != null) {
                    _meals.value = response.meals
                    Log.d(ContentValues.TAG, "Chef's choice : " + "Successfully loaded single random meal.")
                    _status.value = ApiStatus.DONE
                }
                else Log.d(ContentValues.TAG, "Chef's choice: " + "Single random meal loading failed.")

            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _meals.value = listOf()
                Log.d(ContentValues.TAG, "Chef's choice: " + "Single random meal loading failed. Error :" + e.message)
            }
        }

    }


//    //List all meal categories
//    fun loadCategory(){
//        getCategories()
//    }

    //List all meal categories
    private fun getCategories() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val response: MealCategoryResponse?  = repository.getCategories()

                if (response != null) {
                    _category.value = response.categories
                    Log.d(ContentValues.TAG, "Chef's choice : " + "Successfully loaded single random meal.")
                    _status.value = ApiStatus.DONE
                }
                else Log.d(ContentValues.TAG, "Chef's choice: " + "Single random meal loading failed.")

            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _category.value = listOf()
                Log.d(ContentValues.TAG, "Chef's choice: " + "Single random meal loading failed. Error :" + e.message)
            }
        }
    }











}