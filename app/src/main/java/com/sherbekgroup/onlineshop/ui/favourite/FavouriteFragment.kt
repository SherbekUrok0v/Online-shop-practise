package com.sherbekgroup.onlineshop.ui.favourite
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sherbekgroup.onlineshop.R
import com.sherbekgroup.onlineshop.adapter.MyProductAdapter
import com.sherbekgroup.onlineshop.databinding.FragmentFavouriteBinding
import com.sherbekgroup.onlineshop.ui.MainViewModel
import com.sherbekgroup.onlineshop.utils.PrefUtils
import kotlinx.android.synthetic.main.fragment_favourite.*
class FavouriteFragment : Fragment() {
    lateinit var viewModel :MainViewModel
    lateinit var binding:FragmentFavouriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loadData()
        binding =  FragmentFavouriteBinding.inflate(layoutInflater, container, false)
        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.progress.observe(requireActivity(), Observer {
            swipe.isRefreshing = it
        })
        binding.swipe.setOnRefreshListener {
            loadData()
        }
        viewModel.getProductsByIds(PrefUtils.getFavoriteList())
        viewModel.productsData.observe(requireActivity(), Observer {
            binding.rvFav.adapter = MyProductAdapter(it)
        })
        return binding.root
    }
    private fun loadData() {
        viewModel.getProductsByIds(PrefUtils.getFavoriteList())
    }
    companion object {
        @JvmStatic
        fun newInstance() = FavouriteFragment()
    }
}