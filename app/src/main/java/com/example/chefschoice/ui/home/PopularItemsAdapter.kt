package com.example.chefschoice.ui.home
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.chefschoice.data.model.PopularMeal
import com.example.chefschoice.databinding.MostPopularCardBinding


class PopularItemsAdapter(

    private var popularMeals: List<PopularMeal>,
    private val iPopularMeal: IPopularMeal
)
    : RecyclerView.Adapter<PopularItemsAdapter.PopularMealViewHolder>(){

    inner class PopularMealViewHolder(val binding: MostPopularCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        val binding = MostPopularCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        with(holder){
            with(popularMeals[position]){
                binding.txtStrMeal.text = this.strMeal
                binding.imgPopularMeal.load(this.strMealThumb){
                    build()
                }
                binding.mostPopularCardView.setOnClickListener {
                    iPopularMeal.onCellClickListener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return popularMeals.size
    }

}


