package com.turkcell.turkcellsinav1.model

import java.io.Serializable

class Customer(val id: Int): Serializable {
    var phone : String? = null
    var password: String? = null
    private val productsOnBasket = ArrayList<Product>()
    var totalPriceOfProducts : Double = 0.0

    fun getTotalPrice(): String {
        return  "₺" + (String.format("%.2f", totalPriceOfProducts)).replace(".",",")
    }
    fun addProductToBasket(product: Product){
        totalPriceOfProducts += product.price!!
        productsOnBasket.add(product)
    }
}