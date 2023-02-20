//package com.example.chefschoice.data.network.local.dao
//import androidx.room.*
//import com.example.chefschoice.data.model.MealTable
//
//@Dao
//interface IAppDao {
//    // The flow always holds/caches latest version of data. Notifies its observers when the
//    // data has changed.
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertFavorite(meal: MealTable)
//
////    @Update
////    suspend fun updateFavorite(meal:MealTable)
////
////    @Query("SELECT * FROM meal_information order by mealId asc")
////    fun getAllSavedMeals(): Flow<List<MealTable>>
////
////    @Query("SELECT * FROM meal_information WHERE mealId =:id")
////    fun getMealById(id:String):Flow<MealTable>
////
////    @Query("DELETE FROM meal_information WHERE mealId =:id")
////    suspend fun deleteMealById(id:String)
////
////    @Delete
////    suspend fun deleteMeal(meal:MealTable)
//
//}