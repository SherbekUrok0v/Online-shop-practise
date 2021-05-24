package com.sherbekgroup.onlineshop.ui.productdetail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sherbekgroup.onlineshop.R
import com.sherbekgroup.onlineshop.databinding.ActivityProductDetailBinding
import com.sherbekgroup.onlineshop.model.ProductModel
import com.sherbekgroup.onlineshop.utils.Constants
import com.sherbekgroup.onlineshop.utils.PrefUtils
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var productModel: ProductModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productModel = intent.getSerializableExtra("product") as ProductModel
        // status bar text color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        //status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = getColor(R.color.white)
        }
        supportActionBar?.hide()
        binding.cardViewBack.setOnClickListener {
            finish()
        }
        binding.cardViewFavorite.setOnClickListener {
            PrefUtils.setFavorites(productModel)
            if (PrefUtils.checkFavorite(productModel)) {
                binding.ivFav.setImageResource(R.drawable.ic_heart_b)
            } else {
                binding.ivFav.setImageResource(R.drawable.ic_heart_i)
            }
        }
        binding.tvTitle.text = productModel.name
        Glide.with(this).load(Constants.HOST_IMAGE + productModel.image).into(binding.ivDetail)
        binding.tvTitleDetail.text = productModel.name
        binding.tvPrice.text = productModel.price

        if (PrefUtils.getCartCount(productModel) > 0) {
            binding.btnAddCart.visibility = View.GONE
        }

        if (PrefUtils.checkFavorite(productModel)) {
            binding.ivDetail.setImageResource(R.drawable.ic_heart_b)
        } else {
            binding.ivDetail.setImageResource(R.drawable.ic_heart_i)
        }
        binding.btnAddCart.setOnClickListener {
            productModel.cartCount = 1
            PrefUtils.setCart(productModel)
            Toast.makeText(applicationContext, "Product add to cart!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}