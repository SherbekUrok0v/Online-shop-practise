package com.sherbekgroup.onlineshop.ui.cart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sherbekgroup.onlineshop.R
import com.sherbekgroup.onlineshop.adapter.CartAdapter
import com.sherbekgroup.onlineshop.databinding.FragmentCartBinding
import com.sherbekgroup.onlineshop.model.ProductModel
import com.sherbekgroup.onlineshop.ui.MainViewModel
import com.sherbekgroup.onlineshop.ui.MakeOrderActivity
import com.sherbekgroup.onlineshop.utils.Constants
import com.sherbekgroup.onlineshop.utils.PrefUtils
import java.io.Serializable

class CartFragment : Fragment() {
    lateinit var binding: FragmentCartBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        viewModel.progress.observe(requireActivity(), Observer {
            binding.swipe.isRefreshing = it
        })
        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.productsData.observe(requireActivity(), Observer {
            binding.rvCartProduct.adapter = CartAdapter(it)
        })
        binding.swipe.setOnRefreshListener {
            loadData()
        }
        loadData()
        binding.btnMakeOrder.setOnClickListener {
            val intent = Intent(requireContext(), MakeOrderActivity::class.java)
            intent.putExtra(Constants.EXTRA_DATA2,(viewModel.productsData.value?: emptyList<ProductModel>()) as Serializable)
            startActivity(intent)
        }
        return binding.root
    }
    fun loadData(){
        viewModel.getProductsByIds(PrefUtils.getCartList().map { it.product_id })
    }

    companion object {
        @JvmStatic
        fun newInstance() = CartFragment()
    }
}