package com.syafei.optionmenu.data.model

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Authorization: token <Personal Access Token>")

    @GET("api/users")
    fun getListUser(@Query("page") page: String): Call<ResponseUser>

    //get list user by id using path
    @GET("api/users/{id}")
    fun getUser(@Path("id") id: String): Call<ResponseUser>

    //post user using field x-www-form-urlencoded
    @FormUrlEncoded
    @POST("api/users")
    fun crateUser(
        @Field("name") name: String,
        @Field("job") job: String,
    ): Call<ResponseUser>

    //upload file using multipart
    @Multipart
    @PUT("api/uploadfile")
    fun updateUser(
        @Part("file") file: MultipartBody.Part,
        @PartMap data: Map<String, RequestBody>
    ): Call<ResponseUser>


}