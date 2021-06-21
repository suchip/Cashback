package com.example.cashback

import com.example.cashback.models.AddCartResponse
import com.example.cashback.models.Amazon.*
import com.example.cashback.models.Flipkart.FlipkartCategorymainResponse
import com.example.cashback.models.Flipkart.FlipkartProductsResponse
import com.example.cashback.models.Flipkart.FlipkartResponse
import com.example.cashback.models.Flipkart.FlipkartofferResponse
import com.example.cashback.models.GetCartResponse
import com.example.cashback.models.GetUserResponse
import com.example.cashback.models.UserLogin.UserLogin
import com.example.cashback.models.UserLogin.UserLoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface Api {

    @GET
    fun getflipkartofferdetails(@Url url: String?,
        @Header("Fk-Affiliate-Id") fk_affliated_id: String,
    @Header("Fk-Affiliate-Token") fk_token: String
    ): Call<FlipkartResponse>
    @GET("offers/v1/all/json")
    fun getofferdetails(
        @Header("Fk-Affiliate-Id") fk_affliated_id: String,
        @Header("Fk-Affiliate-Token") fk_token: String
    ): Call<FlipkartofferResponse>

    //get offer details
    @GET("1.0/product.json")
    fun getofferdetailss(
        @Header("Fk-Affiliate-Id") fk_affliated_id: String,
        @Header("Fk-Affiliate-Token") fk_token: String,
        @Query("id") product_id: String
    ): Call<FlipkartProductsResponse>



    //get all related products
    @GET("1.0/product.json")
    fun getallrelatedproducts(
        @Header("Fk-Affiliate-Id") fk_affliated_id: String,
        @Header("Fk-Affiliate-Token") fk_token: String,
        @Query("id") product_id: String
    ): Call<FlipkartProductsResponse>


    //get all related products
    @GET("api/makelabsi.json")
    fun getallcategorieslist(
        @Header("Fk-Affiliate-Id") fk_affliated_id: String,
        @Header("Fk-Affiliate-Token") fk_token: String
    ): Call<FlipkartCategorymainResponse>


    //User Mobile Login
    @FormUrlEncoded
    @POST("login/signup/")
    fun Logindetails(
        @Field("login_type") login_type: String,
        @Field("mobile_number") mobile_number: String
    ): Call<UserLogin>

    @FormUrlEncoded
    @POST("amazon/search/")
    fun amazon(
        @Field("key") deals: String
    ): Call<List<AmazonResponse>>


    @FormUrlEncoded
    @POST("wishlist/add/")
    fun addcart_(
        @Field("product_id") product_id: String,
        @Field("platform") platform: String,
        @Header("Authorization") token: String
    ): Call<AddCartResponse>


    @GET("wishlist/me/")
    fun addcart_list(

        @Header("Authorization") token: String
    ): Call<GetCartResponse>

    @GET("profile/get")
    fun get_profile(

        @Header("Authorization") token: String
    ): Call<GetUserResponse>

    @GET("cashback_percentage/")
    fun get_caskbackpercentage(

        @Header("Authorization") token: String
    ): Call<AmazonCashbackResponse>

    @PATCH("profile")
    fun get_profile_update(

        @Header("Authorization") token: String,
        @Field("first_name") first_name: String,
        @Field("email") email: String,
        @Field("mobile") mobile_number: String
    ): Call<ResponseBody>


    @DELETE
    fun addcart_list_delete(
        @Header("Authorization") token: String,

        @Url url: String
    ): Call<ResponseBody>


    @GET
    fun productindetail(
        @Url url: String
    ): Call<AmazonResponse>





}
