package com.syafei.optionmenu.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.syafei.optionmenu.BuildConfig
import com.syafei.optionmenu.R
import com.syafei.optionmenu.data.model.ApiService
import com.syafei.optionmenu.data.model.DataItem
import com.syafei.optionmenu.data.model.ResponseUser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        getDataRetrofit()
        getLoggingInteceptor()
        getLoggingChucker()


    }

    private fun getLoggingChucker() {
        val clientChucker = OkHttpClient.Builder().addInterceptor(ChuckerInterceptor(this)).build()
        val retrofitChuk =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(clientChucker).build()
    }

    private fun getLoggingInteceptor() {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val retrofitIntercept =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(client).build()

        val loggingInterceptorLog = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

    }

    private fun getDataRetrofit() {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val apiService = retrofit.create(ApiService::class.java)

        val clients = apiService.getListUser("1")
        clients.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    val listUser = response.body()?.data as List<DataItem>
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    companion object {
        const val BASE_URL = "https://reqres.in/"
        val TAG: String = ThirdActivity::class.java.simpleName
    }
}