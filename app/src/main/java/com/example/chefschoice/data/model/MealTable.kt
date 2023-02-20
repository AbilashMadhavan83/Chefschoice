package com.example.chefschoice.data.model

data class MealTable(

    val mealId: Int,
    val mealName: String,
    val mealCountry: String,
    val mealCategory:String,
    val mealInstruction:String,
    val mealThumb:String,
    val mealYoutubeLink:String

//    @PrimaryKey(autoGenerate = true) val Id: Int,
//    @ColumnInfo(name = "MealId")val mealId: String,
//    @ColumnInfo(name = "Name")val mealName: String,
//    @ColumnInfo(name = "Country")val mealCountry: String,
//    @ColumnInfo(name = "Category")val mealCategory:String,
//    @ColumnInfo(name = "Instruction")val mealInstruction:String,
//    @ColumnInfo(name = "Thumb")val mealThumb:String,
//    @ColumnInfo(name = "YoutubeLink")val mealYoutubeLink:String
)
