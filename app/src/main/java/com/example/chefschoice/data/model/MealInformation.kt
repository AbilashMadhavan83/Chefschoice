package com.example.chefschoice.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "meal_information")
data class MealInformation(
    @PrimaryKey
    @ColumnInfo(name = "Id") val mealId: Int,
    @ColumnInfo(name = "Name")val mealName: String?,
    @ColumnInfo(name = "Country")val mealCountry: String?,
    @ColumnInfo(name = "Category")val mealCategory:String?,
    @ColumnInfo(name = "Instruction")val mealInstruction:String?,
    @ColumnInfo(name = "Thumb")val mealThumb:String?,
    @ColumnInfo(name = "YoutubeLink")val mealYoutubeLink:String?
): Parcelable
//): Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString()
//    ) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(mealId)
//        parcel.writeString(mealName)
//        parcel.writeString(mealCountry)
//        parcel.writeString(mealCategory)
//        parcel.writeString(mealInstruction)
//        parcel.writeString(mealThumb)
//        parcel.writeString(mealYoutubeLink)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<MealInformation> {
//        override fun createFromParcel(parcel: Parcel): MealInformation {
//            return MealInformation(parcel)
//        }
//
//        override fun newArray(size: Int): Array<MealInformation?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
