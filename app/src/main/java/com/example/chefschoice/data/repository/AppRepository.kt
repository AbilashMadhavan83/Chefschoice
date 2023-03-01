/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.chefschoice.data.repository
import androidx.annotation.WorkerThread
import com.example.chefschoice.data.model.*
import com.example.chefschoice.data.network.local.dao.IAppDao
//import com.example.chefschoice.data.network.local.dao.IAppDao
import com.example.chefschoice.data.network.remote.api.IApiService
import kotlinx.coroutines.flow.Flow


/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class AppRepository(private val iApi: IApiService,private val iDao: IAppDao) {



    //Lookup a single random meal
    suspend fun getSingleRandomMeal(): RandomMealResponse?  =  iApi.getSingleRandomMeal()

    //Filter by Category // find popular item
    suspend fun getPopularItems(category: String?): PopularMealsResponse? = iApi.getPopularItems(category)

    //List all meal categories
    suspend fun getCategories(): MealCategoryResponse?  =  iApi.getCategories()

    //Meals by Category // find item
    suspend fun getMealsByCategory(strCategory: String?): MealsResponse? = iApi.getMealsByCategory(strCategory)

    //Search Meals
    suspend fun getMeals(strMeal:String?): RandomMealResponse?  =  iApi.getMeals(strMeal)


    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allFavorites: Flow<List<MealInformation>> = iDao.getMyFavorites()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertFavorites(mealInformation: MealInformation) {
        iDao.insert(mealInformation)
    }



    //


//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun insertFavorite(meal: MealTable) {
//        iDao.insertFavorite(meal)
//    }


//    suspend fun GetData():List<PostsItem>?  =  iApi.getPostsItem()
//    suspend fun InsertData(newPost: PostsItem?):PostsItem?  =  iApi.InsertPostsItem(newPost)
//    suspend fun UpdateAllData(updPost: PostsItem): PostsItem? =  iApi.UpdateAllData(updPost.id,updPost.userId,updPost.title,updPost.body)
//    suspend fun UpdateSomePortion(id: Int, updPost: PostsItem): PostsItem? =  iApi.UpdateSomePortion(id,updPost)
//    suspend fun DeletePost(id: Int) = iApi.DeletePost(id)
//    suspend fun getProducts(url: String): List<ProductsItem> = iApiSecondURL.getProducts(url)


}
