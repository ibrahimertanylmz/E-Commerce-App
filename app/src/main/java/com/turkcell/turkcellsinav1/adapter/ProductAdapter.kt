package com.turkcell.turkcellsinav1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.turkcell.turkcellsinav1.R
import com.turkcell.turkcellsinav1.model.Customer
import com.turkcell.turkcellsinav1.model.Product

class ProductAdapter(var context: Context, var liste: ArrayList<Product>, var itemClick : (position:Int)->Unit, var addButtonClick : (position:Int)->Unit, var customer: Customer?) : RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.product,parent,false)
        return ProductViewHolder(v,itemClick,addButtonClick,customer)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindData(liste.get(position))
    }

    override fun getItemCount(): Int {
        return liste.size
    }
}