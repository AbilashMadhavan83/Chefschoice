package com.example.chefschoice.data.network.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chefschoice.data.model.MealInformation
import kotlinx.coroutines.flow.Flow


@Dao
interface IAppDao {
    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM meal_information ORDER BY Id ASC")
    fun getMyFavorites(): Flow<List<MealInformation>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mealInformation: MealInformation)

    @Query("DELETE FROM meal_information")
    suspend fun deleteAll()



}