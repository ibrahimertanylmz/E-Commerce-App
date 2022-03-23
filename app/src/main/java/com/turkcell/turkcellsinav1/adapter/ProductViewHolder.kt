package com.turkcell.turkcellsinav1.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.turkcell.turkcellsinav1.R
import com.turkcell.turkcellsinav1.model.Customer
import com.turkcell.turkcellsinav1.model.Product

class ProductViewHolder(itemView: View, var itemClick : (position:Int)->Unit, var addButtonClick : (position:Int)->Unit, customer: Customer? ) : RecyclerView.ViewHolder (itemView) {

    var twName : TextView
    var twBrand : TextView
    var twPrice : TextView
    var productImage : ImageView
    var btnAddtoBasket : Button

    init {
        twName = itemView.findViewById(R.id.twProductName)
        twBrand = itemView.findViewById(R.id.twBrand)
        twPrice = itemView.findViewById(R.id.twPrice)
        productImage = itemView.findViewById(R.id.productImageView)
        btnAddtoBasket = itemView.findViewById(R.id.btnAddtoBasket)

        btnAddtoBasket.setOnClickListener {
            addButtonClick(adapterPosition)
        }
        itemView.setOnClickListener {
            itemClick(adapterPosition)
        }

        if (customer == null){
            btnAddtoBasket.visibility = View.GONE
        }
    }

    fun bindData(product: Product){
        twName.text = product.name
        twBrand.text = product.brand
        twPrice.text =  product.getProductPrice()
        productImage.setImageResource(product.image)
    }
}