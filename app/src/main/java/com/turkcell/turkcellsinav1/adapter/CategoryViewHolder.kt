package com.turkcell.turkcellsinav1.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.turkcell.turkcellsinav1.R
import com.turkcell.turkcellsinav1.model.Category

class CategoryViewHolder(itemView: View, var itemClickCategory : (position:Int)->Unit) : RecyclerView.ViewHolder (itemView) {
    var twCategory : TextView

    init {
        twCategory = itemView.findViewById(R.id.twCategory)
        itemView.setOnClickListener {
            itemClickCategory(adapterPosition)
        }
    }

    fun bindData(category: Category){
        twCategory.text = category.name
        if (category.isSelected){
            twCategory.setBackgroundResource(R.drawable.category_selected_bg)
        }else{
            twCategory.setBackgroundResource(R.drawable.icon_bg)
        }
    }
}