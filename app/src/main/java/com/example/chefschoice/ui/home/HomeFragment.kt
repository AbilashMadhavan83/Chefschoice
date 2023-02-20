package com.example.chefschoice.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.chefschoice.R
import com.example.chefschoice.app.App
import com.example.chefschoice.data.model.MealCategory
import com.example.chefschoice.data.model.MealDetail
import com.example.chefschoice.data.model.PopularMeal
import com.example.chefschoice.data.model.User
import com.example.chefschoice.databinding.FragmentHomeBinding
import com.example.chefschoice.ui.mealDetails.MealsDetailsActivity

class HomeFragment : Fragment(),IPopularMeal,ICategory {

    /**
     * Libraries and technologies used
     */

    private var _binding: FragmentHomeBinding? = null

    private lateinit var adapter: PopularItemsAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var meals: MealDetail

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory((activity?.application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //(activity as AppCompatActivity?)?.supportActionBar?.show()
        //(activity?.application as AppCompatActivity?)?.supportActionBar?.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeFragment.viewModel
        }

        viewModel.loadCategory()

        fun loadCategory(list: List<MealCategory>?) {
            val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this.requireContext(),3,GridLayoutManager.VERTICAL, false)
            categoryAdapter = list?.let { CategoryAdapter(it,this) }!!

            with(binding){
                this.recyclerViewCategory.layoutManager = layoutManager
                this.recyclerViewCategory.adapter = categoryAdapter
                this.recyclerViewCategory.setHasFixedSize(true)
            }
        }
        viewModel.category.observe({ lifecycle }, ::loadCategory)

        viewModel.loadSingleRandomMeal()



        fun loadSingleRandomMeal(meals: MealDetail?) {

            if (meals != null) {

                this@HomeFragment.meals = meals

                binding.imgRandomMeal.load(meals.strMealThumb){
                    build()
                }
                binding.txtStrMeal.text = meals.strMeal



            }
        }
        viewModel.singleRandomMeal.observe({ lifecycle }, ::loadSingleRandomMeal)

        viewModel.loadPopularItems()

        fun loadPopularItems(list: List<PopularMeal>?) {
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.requireContext(),LinearLayoutManager.HORIZONTAL, false)
            adapter = list?.let { PopularItemsAdapter(it,this) }!!

            with(binding){
                this.recViewMealsPopular.layoutManager = layoutManager
                this.recViewMealsPopular.adapter = adapter
                this.recViewMealsPopular.setHasFixedSize(true)
            }
        }
        viewModel.popularMeal.observe({ lifecycle }, ::loadPopularItems)



        // The usage of an interface lets you inject your own implementation
        val menuHost: MenuHost = requireActivity()

        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.search_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        binding.imgRandomMeal.setOnClickListener {

            //val user = User("John Doe", 25)
            val intent = Intent(this@HomeFragment.requireContext(), MealsDetailsActivity::class.java)
            intent.putExtra("meals", this@HomeFragment.meals)
            //intent.putExtra("user", user)
            startActivity(intent)
        }
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCellClickListener(popularMeal: PopularMeal) {
        TODO("Not yet implemented")
    }

    override fun onCellClickListener(category: MealCategory) {
        TODO("Not yet implemented")
    }
}