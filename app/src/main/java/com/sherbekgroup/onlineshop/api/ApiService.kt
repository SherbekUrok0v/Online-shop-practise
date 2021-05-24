package com.sherbekgroup.onlineshop.api

import com.sherbekgroup.onlineshop.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    var retrofit :Retrofit? =null
    var api:Api?=null

    fun apiClient():Api{
        if (retrofit ==null){
             retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
             api = retrofit!!.create(Api::class.java)
        }
        return api!!
    }
}