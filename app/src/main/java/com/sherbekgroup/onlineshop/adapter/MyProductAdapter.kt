package com.sherbekgroup.onlineshop.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sherbekgroup.onlineshop.R
import com.sherbekgroup.onlineshop.model.ProductModel
import com.sherbekgroup.onlineshop.ui.productdetail.ProductDetailActivity
import com.sherbekgroup.onlineshop.utils.Constants
import kotlinx.android.synthetic.main.product_item_layout.view.*

class MyProductAdapter(val products: List<ProductModel>) :
    RecyclerView.Adapter<MyProductAdapter.ItemHolder>() {

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(productModel: ProductModel) {
            itemView.apply {
                tv_prod_title.text = productModel.name
                tv_prod_price.text = productModel.price
                Glide.with(context).load(Constants.HOST_IMAGE+productModel.image).into(iv_product)
            }
            itemView.setOnClickListener {
                val intent = Intent(it.context, ProductDetailActivity::class.java)
                intent.putExtra("product", productModel)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.onBind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}