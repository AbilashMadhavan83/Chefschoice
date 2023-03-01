package com.example.chefschoice.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chefschoice.app.App
import com.example.chefschoice.data.model.MealInformation
import com.example.chefschoice.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() ,IFavorites{

    private var _binding: FragmentFavoritesBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels {
        FavoritesViewModelFactory((activity?.application as App).repository)
    }

    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun loadFavorites(mealInformations: List<MealInformation>?) {

            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
                this.requireContext(),
                LinearLayoutManager.VERTICAL,
                false)
            favoritesAdapter = mealInformations?.let { FavoritesAdapter(it,this) }!!

            with(binding){
                this.idRVFavorites.layoutManager = layoutManager
                this.idRVFavorites.adapter = favoritesAdapter
                this.idRVFavorites.setHasFixedSize(true)
            }

        }
        viewModel.allFavorites.observe({ lifecycle }, ::loadFavorites)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCellClickListener(mealInformation: MealInformation?) {
        if (mealInformation != null) {
            val action = FavoritesFragmentDirections.actionNavigationFavoritesToMealsDetailsFragment(mealInformation)
            findNavController().navigate(action)
        }
//        val intent = Intent(this@FavoritesFragment.requireContext(), MealsDetailsActivity::class.java)
//        intent.putExtra("meals", meal)
//        //intent.putExtra("user", user)
//        startActivity(intent)
    }
}