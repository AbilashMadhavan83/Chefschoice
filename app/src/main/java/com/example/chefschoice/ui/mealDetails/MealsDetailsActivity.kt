package com.example.chefschoice.ui.mealDetails

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.chefschoice.R
import com.example.chefschoice.app.App
import com.example.chefschoice.data.model.MealDetail
import com.example.chefschoice.data.model.MealTable
import com.example.chefschoice.databinding.ActivityMealsDetailsBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class MealsDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealsDetailsBinding
    private lateinit var viewModel: MealsDetailsViewModel
    private lateinit var viewModelFactory: MealsDetailsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_meals_details)
        val meals = intent.extras?.getParcelable<MealDetail>("meals")

        viewModelFactory = MealsDetailsViewModelFactory((application as App).repository, meals)
        viewModel = ViewModelProvider(this, viewModelFactory)[MealsDetailsViewModel::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = this@MealsDetailsActivity.viewModel





        fun loadMealDetail(mealTable: MealTable?) {

            if (mealTable != null) {
                with(binding){

                    imgRandomMeal.load(mealTable.mealThumb){
                        build()
                    }

                    txtStrMeal.text                 =   mealTable.mealName
                    tvInstructions.text             =   "- Instructions : "
                    tvContent.text                  =   mealTable.mealInstruction
                    tvAreaInfoValue.text            =   tvAreaInfoValue.text.toString() + mealTable.mealCountry
                    tvCategoryInfoValue.text        =   tvCategoryInfoValue.text.toString() + mealTable.mealCategory
                    val videoId: String? = ExtractVideoId(mealTable.mealYoutubeLink)

                    youtubePlayerView .addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            if (videoId != null) {
                                youTubePlayer.cueVideo(videoId, 0f)
                            }
                        }
                    })
                }
            }
        }
        viewModel.mealDetail.observe({ lifecycle }, ::loadMealDetail)

//        binding.btnSave.setOnClickListener {
//            viewModel.insertFavorite()
//        }


    }



    private fun ExtractVideoId(strYoutube: String): String? {
        //val youtubeUrl = "https://www.youtube.com/watch?v=X9F1zW8TvbE"
        val videoUri: Uri = Uri.parse(strYoutube)
        var videoId: String? = videoUri.getQueryParameter("v")
        if (videoId == null) {
            videoId = strYoutube.substring(strYoutube.lastIndexOf("/") + 1)
        }
        return videoId

    }
}