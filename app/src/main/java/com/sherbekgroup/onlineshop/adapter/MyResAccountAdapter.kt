package com.sherbekgroup.onlineshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sherbekgroup.onlineshop.R
import com.sherbekgroup.onlineshop.model.AccountModel
import kotlinx.android.synthetic.main.item_user_info.view.*

class MyResAccountAdapter (var list: List<AccountModel>) : RecyclerView.Adapter<MyResAccountAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(accountModel: AccountModel) {
            itemView.apply {
                account_ic.setImageResource(accountModel.image)
                tv_name.text = accountModel.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_user_info, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}