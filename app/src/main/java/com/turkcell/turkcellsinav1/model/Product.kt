package com.turkcell.turkcellsinav1.model

import com.turkcell.turkcellsinav1.R
import java.io.Serializable

class Product(val id: Int) : Serializable {
    var name : String? = null
    var brand : String? = null
    var price: Double? = null
    var details : String? = null
    var image : Int = R.drawable.carts
    var category: Category? = null

    fun getProductPrice() : String{
        return "₺" + price.toString()
    }
}