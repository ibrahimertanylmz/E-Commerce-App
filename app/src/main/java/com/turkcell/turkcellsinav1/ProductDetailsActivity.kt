package com.turkcell.turkcellsinav1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.turkcell.turkcellsinav1.databinding.ActivityProductDetailsBinding
import com.turkcell.turkcellsinav1.model.Customer
import com.turkcell.turkcellsinav1.model.Product

class ProductDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailsBinding
    var customer : Customer? = null
    var product : Product? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customer = intent.getSerializableExtra("customer") as Customer?
        product = intent.getSerializableExtra("product") as Product?

        setProductDetails()

        if (customer!= null){
            binding.twBasketPrice.text = customer!!.getTotalPrice()
            binding.btnAddProduct.setOnClickListener {
                customer!!.addProductToBasket(product!!)
                binding.twBasketPrice.text = customer!!.getTotalPrice()
            }
        }else{
            removeBasketDetails()
        }

        binding.imageBackDetails.setOnClickListener {
            val intentD = Intent()
            intentD.putExtra("customerUpdated", customer)
            setResult(RESULT_OK,intentD)
            finish()
        }
    }

    override fun onBackPressed() {
        val intentD = Intent()
        intentD.putExtra("customerUpdated", customer)
        setResult(RESULT_CANCELED,intentD)
        finish()
    }

    private fun setProductDetails(){
        binding.twUrun.text = product!!.name
        binding.twMarka.text = product!!.brand
        binding.twPriceProduct.text = product!!.getProductPrice()
        binding.twDetails.text = product!!.details
        binding.imageProductDetails.setImageResource(product!!.image)
    }

    private fun removeBasketDetails(){
        binding.twBasketPrice.visibility = View.GONE
        binding.imageBasket.visibility = View.GONE
        binding.btnAddProduct.visibility = View.GONE
    }

}