package com.sherbekgroup.onlineshop.ui.home
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sherbekgroup.onlineshop.adapter.CategoryAdapterListener
import com.sherbekgroup.onlineshop.ui.MainViewModel
import com.sherbekgroup.onlineshop.adapter.MyCategoryAdapter
import com.sherbekgroup.onlineshop.adapter.MyProductAdapter
import com.sherbekgroup.onlineshop.api.Api
import com.sherbekgroup.onlineshop.databinding.FragmentHomeBinding
import com.sherbekgroup.onlineshop.model.BaseResponse
import com.sherbekgroup.onlineshop.model.CategoryModel
import com.sherbekgroup.onlineshop.model.OfferModel
import com.sherbekgroup.onlineshop.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.swipe.setOnRefreshListener {
            loadData()
        }
        viewModel.progress.observe(requireActivity(), Observer {
            binding.swipe.isRefreshing = it
        })
        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.offersData.observe(requireActivity(), Observer {
            binding.carouselView.setImageListener { position, imageView ->
                Glide.with(imageView)
                    .load(Constants.HOST_IMAGE+it[position].image)
                    .into(imageView)
            }
            binding.carouselView.pageCount = it.count()
        })
        binding.rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.categoriesData.observe(requireActivity(), Observer {
            binding.rvCategory.adapter = MyCategoryAdapter(it, object:CategoryAdapterListener{
                override fun onClickItem(item: CategoryModel) {
                    viewModel.getProductsByCategory(item.id)
                }
            })
        })

        viewModel.productsData.observe(requireActivity(), Observer {
            binding.rvProduct.adapter = MyProductAdapter(it)
        })
        loadData();
        return binding.root
    }
    private fun loadData() {
        viewModel.getOffers()
        viewModel.getCategories()
        viewModel.getTopProducts()
    }
    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}