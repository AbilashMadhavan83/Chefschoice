package com.example.chefschoice.ui.mealDetails

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.chefschoice.R
import com.example.chefschoice.app.App
import com.example.chefschoice.data.model.MealInformation
import com.example.chefschoice.databinding.FragmentMealsDetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class MealsDetailsFragment : Fragment() {

    private var _binding: FragmentMealsDetailsBinding? = null

    private val binding get() = _binding!!


    val args: MealsDetailsFragmentArgs by navArgs()

    private val viewModel: MealsDetailsViewModel by viewModels {
        MealsDetailsViewModelFactory((activity?.application as App).repository,args.meals)
    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealsDetailsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnSave.setOnClickListener {
            showFinalScoreDialog()
            //
        }

        fun loadMealDetail(mealInformation: MealInformation?) {

            if (mealInformation != null) {
                with(binding){

                    imgRandomMeal.load(mealInformation.mealThumb){
                        build()
                    }

                    txtStrMeal.text                 =   mealInformation.mealName
                    tvInstructions.text             =   "- Instructions : "
                    tvContent.text                  =   mealInformation.mealInstruction
                    tvAreaInfoValue.text            =   tvAreaInfoValue.text.toString() + mealInformation.mealCountry
                    tvCategoryInfoValue.text        =   tvCategoryInfoValue.text.toString() + mealInformation.mealCategory
                    val videoId: String? = ExtractVideoId(mealInformation.mealYoutubeLink.toString())

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


    }

    /*
    * Creates and shows an AlertDialog with the final score.
    */
    private fun showFinalScoreDialog() {
        MaterialAlertDialogBuilder(this.requireContext())
            .setTitle("Chef's Choice!!!")//getString(R.string.congratulations)
            .setMessage("Do you want to add this item in your favourite list?")//"getString(R.string.you_scored, viewModel.score)"
            .setCancelable(false)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Save") { _, _ ->
                viewModel.insertFavorite()
            }
            .show()

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}