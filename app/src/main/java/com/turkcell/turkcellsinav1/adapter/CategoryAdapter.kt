package com.turkcell.turkcellsinav1.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.turkcell.turkcellsinav1.R
import com.turkcell.turkcellsinav1.model.Category

class CategoryAdapter(var context: Context, var liste: ArrayList<Category>, var itemClickCategory : (position:Int)->Unit) : RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.category,parent,false)
        return CategoryViewHolder(v,itemClickCategory)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindData((liste.get(position)))
    }

    override fun getItemCount(): Int {
        return liste.size
    }
}