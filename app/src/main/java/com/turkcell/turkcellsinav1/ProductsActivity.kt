package com.turkcell.turkcellsinav1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.turkcell.turkcellsinav1.adapter.CategoryAdapter
import com.turkcell.turkcellsinav1.adapter.ProductAdapter
import com.turkcell.turkcellsinav1.databinding.ActivityProductsBinding
import com.turkcell.turkcellsinav1.model.Category
import com.turkcell.turkcellsinav1.model.Customer
import com.turkcell.turkcellsinav1.model.Product

class ProductsActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductsBinding
    private val products  = ArrayList<Product>()
    var waterProducts= ArrayList<Product>()
    var drinkProducts= ArrayList<Product>()
    var foodProducts= ArrayList<Product>()
    private val categories  = ArrayList<Category>()
    var customer : Customer? = null
    var categoryPos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customer = intent.getSerializableExtra("customer") as Customer?

        if (customer == null){
            binding.imageBasket.visibility = View.GONE
            binding.twBasketPrice.visibility = View.GONE
        }

        GridLayoutManager(
                this,
                4,
                RecyclerView.VERTICAL,
                false
        ).apply {
            binding.rwProducts.layoutManager = this
        }

        binding.rwProducts.adapter = ProductAdapter(this,products, ::itemClick, ::addButtonClick,customer)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rwCategories.layoutManager = layoutManager
        binding.rwCategories.adapter = CategoryAdapter(this,categories, ::itemClickCategory)

        addCategories()
        addWaterProducts()
        addDrinkProducts()
        addFoodProducts()
        addProducts()

        categories[0].isSelected = true
        addProductsToCategories()

        binding.imageBack.setOnClickListener {
            finish()
        }

    }

    private var resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_CANCELED) {
                    val gelenData: Intent? = result.data
                    val customerUpdated = gelenData?.getSerializableExtra("customerUpdated") as Customer?
                    if (customerUpdated!=null){
                        customer = customerUpdated
                        binding.twBasketPrice.text = customer!!.getTotalPrice()
                    }
                }
                if (result.resultCode == RESULT_OK) {
                    val gelenData: Intent? = result.data
                    val customerUpdated = gelenData?.getSerializableExtra("customerUpdated") as Customer?
                    if (customerUpdated!=null){
                        customer = customerUpdated
                        binding.twBasketPrice.text = customer!!.getTotalPrice()
                    }
                }
            }

    private fun itemClick(position: Int) {
        if (customer!=null){
            val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra("customer",customer)
            intent.putExtra("product",(categories[categoryPos]).products[position])
            resultLauncher.launch(intent)
        }else{
            val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra("product",(categories[categoryPos]).products[position])
            resultLauncher.launch(intent)
        }
    }

    private fun itemClickCategory(position: Int) {
        categories.forEach {
            it.isSelected = false
        }
        categories[position].isSelected = true
        binding.rwCategories.adapter!!.notifyDataSetChanged()
        categoryPos = position
        binding.rwProducts.adapter = ProductAdapter(this,categories[position].products, ::itemClick, ::addButtonClick,customer)
    }

    private fun addButtonClick(position: Int){
        customer!!.addProductToBasket((categories[categoryPos]).products[position])
        binding.twBasketPrice.text = customer!!.getTotalPrice()
    }

    private fun addProducts(){
        products.addAll(waterProducts)
        products.addAll(drinkProducts)
        products.addAll(foodProducts)
        binding.rwProducts.adapter?.notifyDataSetChanged()
    }

    private fun addWaterProducts(){
        for(i in 0..20){
            val product  = Product(i)
            product.name = "Su"
            product.brand = "Hayat"
            product.price =  11.90
            product.details = "Ürüne dair detay açıklama (su)"
            product.image = R.drawable.bottle
            waterProducts.add(product)
        }
    }

    private fun addDrinkProducts(){
        for(i in 0..20){
            val product  = Product(i)
            product.name = "Gazoz"
            product.brand = "Gazozcum"
            product.price =  16.45
            product.details = "Ürüne dair detay açıklama (gazoz)"
            product.image = R.drawable.drinks
            drinkProducts.add(product)
        }
    }

    private fun addFoodProducts(){
        for(i in 0..20){
            val product  = Product(i)
            product.name = "Cips"
            product.brand = "Chipss"
            product.price =  8.85
            product.details = "Ürüne dair detay açıklama (cips)"
            product.image = R.drawable.chips
            foodProducts.add(product)
        }
    }

    private fun addCategories(){
        val categoryAll  = Category(0)
        categoryAll.name = "Tümü"
        categories.add(categoryAll)
        val category  = Category(1)
        category.name = "Su"
        categories.add(category)

        val category2  = Category(2)
        category2.name = "İçecek"
        categories.add(category2)

        val category3  = Category(3)
        category3.name = "Yiyecek"
        categories.add(category3)

        val category4  = Category(4)
        category4.name = "Meyve Suyu"
        categories.add(category4)

        val category5  = Category(5)
        category5.name = "Gazlı İçecek"
        categories.add(category5)

        val category6  = Category(6)
        category6.name = "Oyuncak"
        categories.add(category6)

        binding.rwCategories.adapter?.notifyDataSetChanged()
    }

    private fun addProductsToCategories(){
        categories[0].products = products
        categories[1].products = waterProducts
        categories[2].products = drinkProducts
        categories[3].products = foodProducts
    }
}