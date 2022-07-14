package com.syafei.optionmenu.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import com.syafei.optionmenu.R
import cz.msebera.android.httpclient.Header

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val client = AsyncHttpClient()
        val url = "https://reqres.in/api/users"
        val params = RequestParams()
        params.put("name", "hazwar syechzad asyafei")
        params.put("job", "main sway di taman")

        //method get
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                TODO("Not yet implemented")
            }

        })

        //method post mengirim data
        client.post(url, params, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                TODO("Not yet implemented")
            }

        })

        //method update/put
        client.put(url, params, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                TODO("Not yet implemented")
            }

        })

        //method delete
        client.delete(url, params, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                TODO("Not yet implemented")
            }

        })

    }
}