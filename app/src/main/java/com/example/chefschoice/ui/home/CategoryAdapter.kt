package com.example.chefschoice.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.chefschoice.data.model.MealCategory
import com.example.chefschoice.databinding.CategoryCardBinding

class CategoryAdapter(
    private var categories: List<MealCategory>,
    private val iCategory: ICategory
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){
    inner class CategoryViewHolder(val binding: CategoryCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        with(holder){
            with(categories[position]){
                binding.tvCategoryName .text = this.strCategory
                binding.imgCategory .load(this.strCategoryThumb){
                    build()
                }
                binding.imgCategoryBorder.setOnClickListener {
                    iCategory.onCellClickListener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}