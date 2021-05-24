package com.sherbekgroup.onlineshop.api

import com.sherbekgroup.onlineshop.model.BaseResponse
import com.sherbekgroup.onlineshop.model.CategoryModel
import com.sherbekgroup.onlineshop.model.OfferModel
import com.sherbekgroup.onlineshop.model.ProductModel
import com.sherbekgroup.onlineshop.model.request.GetProductsByIdRequest
import io.reactivex.Observable
//import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @GET("get_offers")
    fun getOffers(): Observable<BaseResponse<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories(): Observable<BaseResponse<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProducts(): Observable<BaseResponse<List<ProductModel>>>

    @GET("get_products/{category_id}")
    fun getCategoryProducts(@Path("category_id") categoryId :Int): Observable<BaseResponse<List<ProductModel>>>

    @POST("get_products_by_ids")
    fun getProductsById(@Body request:GetProductsByIdRequest): Observable<BaseResponse<List<ProductModel>>>

}