package com.sherbekgroup.onlineshop.adapter

import android.graphics.Color
import android.hardware.camera2.CameraManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sherbekgroup.onlineshop.R
import com.sherbekgroup.onlineshop.model.CategoryModel
import kotlinx.android.synthetic.main.category_item_layout.view.*

interface CategoryAdapterListener {
    fun onClickItem(item: CategoryModel)
}

class MyCategoryAdapter(
    val items: List<CategoryModel>, val callBack:CategoryAdapterListener
) : RecyclerView.Adapter<MyCategoryAdapter.ItemHolder>() {

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(categoryModel: CategoryModel) {
            itemView.apply {
                tv_title.text = categoryModel.title
                if (categoryModel.checked) {
                    cardView.setCardBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.colorPrimary
                        )
                    )
                    tv_title.setTextColor(Color.WHITE)
                } else {
                    cardView.setCardBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.grey
                        )
                    )
                    tv_title.setTextColor(Color.BLACK)
                }
            }
            itemView.setOnClickListener {

                items.forEach {
                    it.checked =false
                }
                categoryModel.checked = true
                callBack.onClickItem(categoryModel)
              notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}