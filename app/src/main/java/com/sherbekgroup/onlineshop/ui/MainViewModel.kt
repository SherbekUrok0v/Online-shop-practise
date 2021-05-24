package com.sherbekgroup.onlineshop.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sherbekgroup.onlineshop.api.repository.ShopRepository
import com.sherbekgroup.onlineshop.model.CategoryModel
import com.sherbekgroup.onlineshop.model.OfferModel
import com.sherbekgroup.onlineshop.model.ProductModel

class MainViewModel : ViewModel() {

    val repository =ShopRepository()
    val error = MutableLiveData<String>()
    val progress = MutableLiveData<Boolean>()
    val offersData = MutableLiveData<List<OfferModel>>()
    val categoriesData = MutableLiveData<List<CategoryModel>>()
    val productsData = MutableLiveData<List<ProductModel>>()
    fun getOffers(){
        repository.getOffers(error, progress,offersData)
    }
    fun getCategories(){
        repository.getCategories(error, categoriesData)
    }
    fun getTopProducts(){
        repository.getTopProducts(error, productsData)
    }
    fun getProductsByCategory(id:Int){
        repository.getProductsByCategory(id,error,productsData)
    }
    fun getProductsByIds(ids:List<Int>){
        repository.getProductsByIds(ids, error, progress, productsData)
    }
}