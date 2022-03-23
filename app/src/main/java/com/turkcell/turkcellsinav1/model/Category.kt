package com.turkcell.turkcellsinav1.model

class Category(val id:Int) {
    var name : String? = null
    var isSelected : Boolean = false
    var products = ArrayList<Product>()
}