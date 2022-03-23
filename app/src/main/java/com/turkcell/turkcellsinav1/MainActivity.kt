package com.turkcell.turkcellsinav1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.turkcell.turkcellsinav1.databinding.ActivityMainBinding
import com.turkcell.turkcellsinav1.model.Customer

class MainActivity : AppCompatActivity() {
    private val customer = Customer(0)
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customer.phone = "01111111111"
        customer.password = "#e&it1m"

        binding.btnLogin.setOnClickListener {
            if(binding.edtPhone.text.toString() == customer.phone && binding.edtPassword.text.toString() == customer.password){
                val intent = Intent(this, ProductsActivity::class.java)
                intent.putExtra("customer",customer)
                startActivity(intent)
            }else{
                Toast.makeText(this,getString(R.string.login_error),Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnContinueUnregistered.setOnClickListener {
            val intent = Intent(this, ProductsActivity::class.java)
           startActivity(intent)
        }
    }
}