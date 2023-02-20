package com.example.chefschoice.ui.categories
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chefschoice.app.App
import com.example.chefschoice.data.model.Meal
import com.example.chefschoice.data.model.MealCategory
import com.example.chefschoice.databinding.FragmentCategoriesBinding
import com.example.chefschoice.ui.home.CategoryAdapter


class CategoriesFragment : Fragment(), ICategories,IMeals  {

    private var _binding: FragmentCategoriesBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: CategoriesViewModel by viewModels {
        CategoriesViewModelFactory((activity?.application as App).repository)
    }

    private lateinit var categoryAdapter: CategoriesAdapter
    private lateinit var mealItemAdapter: MealItemAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@CategoriesFragment.viewModel
        }

        fun loadCategory(mealCategories: List<MealCategory>?) {
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
            //GridLayoutManager(this.requireContext(),3,GridLayoutManager.VERTICAL, false)
            categoryAdapter = mealCategories?.let { CategoriesAdapter(this.requireContext(),it,this) }!!

            with(binding){
                this.recyclerViewCategory.layoutManager = layoutManager
                this.recyclerViewCategory.adapter = categoryAdapter
                this.recyclerViewCategory.setHasFixedSize(true)

            }

        }
        viewModel.category.observe({ lifecycle }, ::loadCategory)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCellClickListener(category: MealCategory) {

        viewModel.getMealsByCategory(category)
        fun loadMeals(meals: List<Meal>?) {
            val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this.requireContext(),3,
                GridLayoutManager.VERTICAL, false)
            mealItemAdapter = meals?.let { MealItemAdapter(it,this) }!!

            with(binding){
                this.recyclerViewMealItem.layoutManager = layoutManager
                this.recyclerViewMealItem.adapter = mealItemAdapter
                this.recyclerViewMealItem.setHasFixedSize(true)
            }
        }
        viewModel.meals.observe({ lifecycle }, ::loadMeals)
    }

    override fun onCellClickListener(meal: Meal) {
        TODO("Not yet implemented")
    }


}