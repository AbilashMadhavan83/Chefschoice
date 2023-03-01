package com.example.chefschoice.data.network.remote.api
import com.example.chefschoice.data.model.MealCategoryResponse
import com.example.chefschoice.data.model.MealsResponse
import com.example.chefschoice.data.model.PopularMealsResponse
import com.example.chefschoice.data.model.RandomMealResponse
import retrofit2.Call
import retrofit2.http.*

interface IApiService {

    //Lookup a single random meal
    //https://www.themealdb.com/api/json/v1/1/random.php
    @GET ("random.php")
    suspend fun getSingleRandomMeal():RandomMealResponse?

    //Filter by Category // find popular item
    // https://www.themealdb.com/api/json/v1/1/filter.php?&c=Seafood
    @GET("filter.php?")
    suspend fun getPopularItems(@Query ("c") category:String?): PopularMealsResponse?

    //List all meal categories
    //https://www.themealdb.com/api/json/v1/1/categories.php
    @GET("categories.php")
    suspend fun getCategories(): MealCategoryResponse?


    //https://www.themealdb.com/api/json/v1/1/filter.php?&i=Beef
    @GET("filter.php?")
    suspend fun getMealsByCategory(@Query("c") strCategory:String?): MealsResponse?

    //www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata
    @GET("search.php?")
    suspend fun getMeals(@Query("s") strMeal:String?): RandomMealResponse?







//    @GET("posts")
//    suspend fun getPostsItem(): List<PostsItem>?
//
//    @POST("posts")
//    suspend fun InsertPostsItem(@Body newPost: PostsItem?): PostsItem?
//
//    @FormUrlEncoded
//    @PUT("posts/{id}")
//    suspend fun UpdateAllData(
//        @Path("id") id:Int?,
//        @Field("userId") userId:Int?,
//        @Field("title") title:String,
//        @Field("body") body:String
//    ): PostsItem?
//
//    @PATCH("posts/{id}")
//    suspend fun UpdateSomePortion(@Path("id") id:Int?,@Body post: PostsItem): PostsItem?
//
//    @DELETE("posts/{id}")
//    suspend fun DeletePost(@Path("id") id:Int?)

}