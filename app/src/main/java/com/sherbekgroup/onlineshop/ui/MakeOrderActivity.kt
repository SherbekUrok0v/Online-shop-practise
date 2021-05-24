package com.sherbekgroup.onlineshop.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sherbekgroup.onlineshop.MapsActivity
import com.sherbekgroup.onlineshop.R
import com.sherbekgroup.onlineshop.model.AddressModel
import com.sherbekgroup.onlineshop.model.ProductModel
import com.sherbekgroup.onlineshop.utils.Constants
import kotlinx.android.synthetic.main.activity_make_order.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MakeOrderActivity : AppCompatActivity() {
    var address: AddressModel? = null
    lateinit var items: List<ProductModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_order)
        supportActionBar?.hide()

        items = intent.getSerializableExtra(Constants.EXTRA_DATA2) as List<ProductModel>

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        tvTotalAmount.setText(items.sumByDouble { it.cartCount.toDouble() * (it.price.replace(" ", "").toDoubleOrNull() ?: 0.0)}.toString())
        etAddress.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onEvent(address: AddressModel) {
        this.address = address
        etAddress.setText("${address.latitude}, ${address.longitude}")
    }
}